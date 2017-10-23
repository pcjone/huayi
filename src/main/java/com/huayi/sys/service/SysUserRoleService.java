package com.huayi.sys.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.sys.model.SysUserRole;


public interface SysUserRoleService extends BaseService<SysUserRole>{
	public List<Long> queryRoleInfo(Map<String,Object> params);
}
