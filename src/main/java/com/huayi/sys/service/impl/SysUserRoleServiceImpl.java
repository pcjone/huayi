package com.huayi.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.sys.dao.SysUserRoleMapper;
import com.huayi.sys.model.SysUserRole;
import com.huayi.sys.service.SysUserRoleService;

@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements SysUserRoleService{

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Override
	public PageInfo<SysUserRole> query(Map<String, Object> params) throws ServiceException {
		return null;
	}	

	@Override
	protected BaseMapper<SysUserRole> getMapper() {
		return sysUserRoleMapper;
	}
	
	@Override
	public List<Long> queryRoleInfo(Map<String, Object> params) {
		return sysUserRoleMapper.queryRoleInfo(params);
	}

}
