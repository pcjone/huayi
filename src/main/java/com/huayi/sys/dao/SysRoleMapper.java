package com.huayi.sys.dao;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.sys.model.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole> {
	public SysRole validateName(String roleName);
	public List<SysRole> queryAll(Map<String, Object> params);
}
