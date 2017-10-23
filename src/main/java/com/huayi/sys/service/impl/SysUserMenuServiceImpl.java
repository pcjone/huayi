package com.huayi.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.sys.dao.SysUserMenuMapper;
import com.huayi.sys.model.SysUserMenu;
import com.huayi.sys.service.SysUserMenuService;

@Service
public class SysUserMenuServiceImpl extends BaseServiceImpl<SysUserMenu> implements SysUserMenuService {

	@Autowired
	private SysUserMenuMapper sysUserMenuMapper;
	
	@Override
	public PageInfo<SysUserMenu> query(Map<String, Object> params) throws ServiceException {
		return null;
	}

	@Override
	protected BaseMapper<SysUserMenu> getMapper() {
		return sysUserMenuMapper;
	}
	
}
