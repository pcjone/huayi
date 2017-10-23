package com.huayi.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.huayi.common.Constants;
import com.huayi.common.base.BaseController;
import com.huayi.common.session.UserSession;
import com.huayi.common.util.HtmlUtil;
import com.huayi.common.util.WebUtil;
import com.huayi.sys.model.SysUser;
import com.huayi.sys.model.expand.MenuTree;
import com.huayi.sys.model.expand.SysMenuExpand;
import com.huayi.sys.service.SysMenuService;
import com.huayi.sys.service.SysUserService;

/**
 * 
* @ClassName: MainController 
* @Description: 控制器
* @author panlei
* @date 2017年7月20日 上午11:36:42 
*
 */
@Controller
public class MainController extends BaseController{
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("main")
	public Object index(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		UserSession userSession = WebUtil.getCurrentUserSession();
		Map<String,Object> params = new HashMap<String,Object>();
		Long userId = userSession.getId();
		params.put("userId", userId);
		params.put("enable", Constants.ENABLE_NO);
		params.put("isSuper", userSession.getIsSuper());
		//!=0 菜单权限
		params.put("menuType", 0);
		List<SysMenuExpand> tree = sysMenuService.queryMenuListByUserId(params);
		context.put("menuTree", tree);
		SysUser user = sysUserService.queryDBById(userId);
		context.put("user", user);
		return forword("main",context);
	}
	
	@RequestMapping("main/tree")
	public void mainTree(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		UserSession userSession = WebUtil.getCurrentUserSession();
		Map<String,Object> params = new HashMap<String,Object>();
		Long userId = userSession.getId();
		params.put("userId", userId);
		params.put("enable", Constants.ENABLE_NO);
		//!=0 菜单权限
		params.put("menuType", 0);
		List<SysMenuExpand> tree = sysMenuService.queryMenuListByUserId(params);
		List<MenuTree> nodes = new ArrayList<MenuTree>();		
		MenuTree menuTree = new MenuTree();
		menuTree.setId("1");
		menuTree.setText("我的工作台");
		menuTree.setIsHeader(true);
		nodes.add(menuTree);
		for(SysMenuExpand menu :  tree) {
			MenuTree child = new MenuTree();
			child.setId(menu.getId().toString());
			child.setText(menu.getMenuName());
			child.setIcon(menu.getIconcls());
			child.setIsOpen(false);
			if(menu.isHasChild()) {
				createMenuTree(child,menu.getChildSysMenu());
			}else {
				child.setTargetType("iframe-tab");
				child.setUrl(menu.getRequest());
			}
			nodes.add(child);
		}
		SysUser user = sysUserService.queryDBById(userId);
		context.put("user", user);
		context.put("menus", nodes);
		HtmlUtil.writerJson(response, context);
	}
	
	private void createMenuTree(MenuTree menuTree,List<SysMenuExpand> trees) {
		List<MenuTree> nodes = new ArrayList<MenuTree>();
		for(SysMenuExpand tree :  trees) {
			MenuTree child = new MenuTree();
			child.setId(tree.getId().toString());
			child.setText(tree.getMenuName());
			child.setIcon(tree.getIconcls());
			child.setIsOpen(false);
			if(tree.isHasChild()) {
				createMenuTree(child,tree.getChildSysMenu());
			}else {
				child.setTargetType("iframe-tab");
				child.setUrl(tree.getRequest());
			}
			nodes.add(child);
		}
		menuTree.setChildren(nodes);
	}
}
