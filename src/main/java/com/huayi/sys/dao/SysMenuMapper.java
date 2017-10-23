package com.huayi.sys.dao;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.sys.model.SysMenu;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
	/**
	 * 
	* @Title: queryMenuTree 
	* @Description: 登录查询用户顶级菜单
	* @param @param params
	* @param @return     
	* @return List<SysMenuTree>    
	* @throws
	 */
	public List<SysMenu> queryMenuListByUserId(Map<String,Object> params);
	/**
	 * 
	* @Title: querySysMenuByRoleId 
	* @Description: 根据角色查询所有权限
	* @param @param params
	* @param @return     
	* @return List<SysMenu>
	* @throws
	 */
	public List<SysMenu> querySysMenuByRoleId(Map<String,Object> params);
	
	/**
	 * 
	* @Title: queryListMenuTree 
	* @Description: 查询权限树
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> queryListMenuTree(Map<String,Object> params);
	/**
	 * 
	* @Title: querySysMenuAll 
	* @Description: 查询所有权限
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> querySysMenuAll(Map<String, Object> params);
	
	/**
	 * 
	* @Title: querySysMenuSon 
	* @Description: 查询子菜单
	* @param @param params
	* @param @return     
	* @return List<SysMenu>    
	* @throws
	 */
	public List<SysMenu> querySysMenuSon(Map<String, Object> params);
}
