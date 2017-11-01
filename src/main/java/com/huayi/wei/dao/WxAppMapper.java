package com.huayi.wei.dao;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.wei.model.WxApp;

public interface WxAppMapper extends BaseMapper<WxApp>{
	public List<WxApp> queryAll(Map<String, Object> params);
}
