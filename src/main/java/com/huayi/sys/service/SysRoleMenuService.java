package com.huayi.sys.service;

import java.util.List;

import com.huayi.common.base.BaseService;
import com.huayi.sys.model.SysRoleMenu;


public interface SysRoleMenuService extends BaseService<SysRoleMenu>{
	public List<Long> queryMenuIdByRoleId(Long roleId);
}
