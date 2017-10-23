package com.huayi.wei.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.wei.model.WxApp;

public interface WxAppService extends BaseService<WxApp>{
	public List<WxApp> queryAll(Map<String,Object> params);
}
