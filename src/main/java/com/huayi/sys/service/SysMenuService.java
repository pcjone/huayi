package com.huayi.sys.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.sys.model.SysMenu;
import com.huayi.sys.model.expand.SysMenuExpand;

public interface SysMenuService extends BaseService<SysMenu>{
	/**
	 * 
	* @Title: querySysMenuByUserId 
	* @Description: 登录查询用户权限(组装成tree)
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenuExpand> queryMenuListByUserId(Map<String,Object> params);
	/**
	 * 
	* @Title: queryPermissionByUserId 
	* @Description: 查询shiro权限
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> queryPermissionByUserId(Map<String,Object> params);
	/**
	 * 
	* @Title: querySysMenuByRolerId 
	* @Description: 根据角色查询所有菜单树
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> querySysMenuByRolerId(Map<String,Object> params);	
	/**
	 * 
	* @Title: querySysMenuAll 
	* @Description: 查询所有权限
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> querySysMenuAll(Map<String,Object> params);	
	/**
	 * 
	* @Title: querySysMenuSon 
	* @Description: 查询子菜单
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> querySysMenuSon(Map<String,Object> params);	
	
	/**
	 * 
	* @Title: queryListMenuTree 
	* @Description: 查询所有菜单树
	* @param @param params
	* @param @return     
	* @return List<SysMenuTree>    
	* @throws
	 */
	public List<SysMenuExpand> queryListMenuTree(Map<String,Object> params);
	
}
