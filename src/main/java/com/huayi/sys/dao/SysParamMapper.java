package com.huayi.sys.dao;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.sys.model.SysParam;

public interface SysParamMapper extends BaseMapper<SysParam>{
	public List<SysParam> querySysParam(Map<String, Object> params);
	public SysParam queryByKey(String paramKey);
}
