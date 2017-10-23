package com.huayi.sys.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.sys.model.SysParam;


public interface SysParamService extends BaseService<SysParam>{
	public List<SysParam> validateSysParam(Map<String,Object> params);
	
	public SysParam queryByKey(String paramKey);
}
