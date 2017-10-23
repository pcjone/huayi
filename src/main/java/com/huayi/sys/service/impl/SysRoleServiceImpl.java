package com.huayi.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huayi.common.Constants;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.config.Resources;
import com.huayi.common.exception.ServiceException;
import com.huayi.sys.dao.SysRoleMapper;
import com.huayi.sys.dao.SysRoleMenuMapper;
import com.huayi.sys.dao.SysUserRoleMapper;
import com.huayi.sys.model.SysRole;
import com.huayi.sys.model.SysRoleMenu;
import com.huayi.sys.service.SysRoleService;
import com.github.pagehelper.PageInfo;
/**
 * 
* @ClassName: SysRoleServiceImpl 
* @Description: 角色管理服务
* @author panlei
* @date 2017年7月28日 下午6:15:38 
*
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Override
	public PageInfo<SysRole> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<SysRole> getMapper() {
		return sysRoleMapper;
	}

	@Override
	public void validateName(String roleName) {
		SysRole role = sysRoleMapper.validateName(roleName);
		if(role != null) {
			throw new ServiceException(Resources.getMessage("角色名称已存在"));
		}
	}
	
	@Transactional
	@Override
	public void deleteDBAndCache(Long[] ids) {
		super.deleteDBAndCache(ids);
		List<Long> list = new ArrayList<Long>();
		for(Long id : ids) {
			list.add(id);
		}
		sysRoleMenuMapper.deleteAllByRoleIds(list);
		sysUserRoleMapper.deleteAllByRoleIds(list);
	}
	
	@Transactional
	@Override
	public void deleteDB(Long[] ids) {
		super.deleteDBAndCache(ids);
		List<Long> list = new ArrayList<Long>();
		for(Long id : ids) {
			list.add(id);
		}
		sysRoleMenuMapper.deleteAllByRoleIds(list);
		sysUserRoleMapper.deleteAllByRoleIds(list);
	}

	@Transactional
	@Override
	public void addOrDeleteSysRoleMenu(List<Long> addMenuIds, List<Long> deleteMenuIds,Long roleId,String createBy) {
		List<SysRoleMenu> addSysRoleMenus = new ArrayList<SysRoleMenu>();
		for(Long menu : addMenuIds) {
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setMenuId(menu);
			sysRoleMenu.setRoleId(roleId);
			sysRoleMenu.setEnable(Constants.ENABLE_NO);
			sysRoleMenu.setCreateBy(createBy);
			sysRoleMenu.setCreateTime(new Date());
			addSysRoleMenus.add(sysRoleMenu);
		}
		if(addSysRoleMenus!= null && addSysRoleMenus.size()>0) {
			sysRoleMenuMapper.insertBatch(addSysRoleMenus);
		}
		for(Long menu : deleteMenuIds) {
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setMenuId(menu);
			sysRoleMenu.setRoleId(roleId);
			sysRoleMenuMapper.deleteByRecord(sysRoleMenu);
		}
	}

	@Override
	public List<SysRole> queryAll(Map<String, Object> params) {
		return sysRoleMapper.queryAll(params);
	}
}
