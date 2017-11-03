package com.huayi.wei.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.wei.model.WxNews;

public interface WxNewsService extends BaseService<WxNews>{
	List<WxNews> queryAllList(Map<String,Object> params);
}
