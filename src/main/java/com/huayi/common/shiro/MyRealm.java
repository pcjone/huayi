package com.huayi.common.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.huayi.common.Constants;
import com.huayi.common.session.UserSession;
import com.huayi.common.util.CacheUtil;
import com.huayi.common.util.WebUtil;
import com.huayi.sys.model.SysMenu;
import com.huayi.sys.model.SysUser;
import com.huayi.sys.service.SysMenuService;
import com.huayi.sys.service.SysUserMenuService;
import com.huayi.sys.service.SysUserService;

/**
 * 
* @ClassName: Realm 
* @Description: 登录验证+权限
* @author panlei
* @date 2017年7月19日 上午11:27:23
*
 */
public class MyRealm extends AuthorizingRealm{
	
	private final Logger logger = LogManager.getLogger();
	
	@Autowired
	protected SysUserService sysUserService;
	
	@Autowired
	protected SysUserMenuService sysUserMenuService;
	
	@Autowired
	protected SysMenuService sysMenuService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		UserSession userSession = WebUtil.getCurrentUserSession();
		logger.info("add permission!");
		for(String permission : userSession.getPermissionList()) {
			if(StringUtils.isNotBlank(permission)) {
				// 添加基于Permission的权限信息
				info.addStringPermission(permission);
			}
		}
		info.addStringPermission("user");
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enable", Constants.ENABLE_NO);
		params.put("account", token.getUsername());
		SysUser user = sysUserService.queryUserByName(params);
		if(user != null) {
			StringBuilder sb = new StringBuilder(100);
			for (int i = 0; i < token.getPassword().length; i++) {
				sb.append(token.getPassword()[i]);
			}
			if (user.getPassword().equals(sb.toString())) {
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(),
						getName());
				logger.info("AuthenticationInfo save!");
				WebUtil.saveCurrentUser(createUserSession(user));
				return authcInfo;
			}
			logger.warn("USER [{}] PASSWORD IS WRONG: {}", token.getUsername(), sb.toString());
			return null;
		}else {
			logger.warn("No user: {}", token.getUsername());
			return null;
		}
	}
	
	private String createUserSession(SysUser user) {
		UserSession userSession = new UserSession();
		userSession.setId(user.getId());
		userSession.setAccount(user.getAccount());
		userSession.setUserType(user.getUserType());
		userSession.setIsSuper(user.getIsSuper());
		userSession.setUserName(user.getUserName());
		//是否是超级管理员
		if(user.getIsSuper() == Constants.SUPER_ADMIN_YES) {
			List<SysMenu> sysMenuList = sysMenuService.querySysMenuAll(null);
			List<String> permissionList = new ArrayList<String>();
			for(SysMenu menu : sysMenuList) {
				permissionList.add(menu.getPermission());
			}
			userSession.setPermissionList(permissionList);
		}else {
			//根据用户id查询权限菜单
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", user.getId());
			params.put("enable", Constants.ENABLE_NO);
			//权限（操作+菜单）
			params.put("menuType", -1);
			List<SysMenu> sysMenuList = sysMenuService.queryPermissionByUserId(params);
			List<String> permissionList = new ArrayList<String>();
			for(SysMenu menu : sysMenuList) {
				permissionList.add(menu.getPermission());
			}
			userSession.setPermissionList(permissionList);
		}
		CacheUtil.getRedisCacheHelper().setNoExpiry(userSession.getAccount(), userSession);
		return userSession.getAccount();
	}

}
