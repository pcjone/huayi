package com.huayi.wei.dao;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.wei.model.WxNews;

public interface WxNewsMapper extends BaseMapper<WxNews>{
	public List<WxNews> queryAllList(Map<String, Object> params);
}
