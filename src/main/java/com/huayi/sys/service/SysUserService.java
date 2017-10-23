package com.huayi.sys.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.sys.model.SysUser;

public interface SysUserService extends BaseService<SysUser>{
	
	public int updatePassword(SysUser record);
	
	public SysUser queryUserByName(Map<String, Object> params);
	
	public void addOrDeleteSysRoleUser(List<Long> addRoleIds,List<Long> deleteRoleIds,Long userId,String curUser);
}
