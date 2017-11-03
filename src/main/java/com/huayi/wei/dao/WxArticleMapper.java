package com.huayi.wei.dao;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.wei.model.WxArticle;

public interface WxArticleMapper extends BaseMapper<WxArticle>{
	public List<WxArticle> queryAllList(Map<String,Object> params);
}
