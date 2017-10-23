package com.huayi.sys.dao;

import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.sys.model.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser>{
	public SysUser queryUserByName(Map<String, Object> params);
	
	public int updatePassword(SysUser record);
}
