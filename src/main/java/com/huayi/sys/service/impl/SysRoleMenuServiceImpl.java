package com.huayi.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.sys.dao.SysRoleMenuMapper;
import com.huayi.sys.model.SysRoleMenu;
import com.huayi.sys.service.SysRoleMenuService;
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenu> implements SysRoleMenuService {

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	@Override
	public PageInfo<SysRoleMenu> query(Map<String, Object> params) throws ServiceException {
		return null;
	}

	@Override
	protected BaseMapper<SysRoleMenu> getMapper() {
		return sysRoleMenuMapper;
	}

	@Override
	public List<Long> queryMenuIdByRoleId(Long roleId) {
		return sysRoleMenuMapper.queryMenuIdByRoleId(roleId);
	}

}
