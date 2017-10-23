package com.huayi.sys.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.sys.model.SysRole;


public interface SysRoleService extends BaseService<SysRole>{
	public void validateName(String roleName);
	public void addOrDeleteSysRoleMenu(List<Long> addMenuIds,List<Long> deleteMenuIds,Long roleId,String createBy);
	public List<SysRole> queryAll(Map<String,Object> params);
}
