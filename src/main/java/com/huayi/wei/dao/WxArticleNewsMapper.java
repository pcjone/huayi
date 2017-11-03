package com.huayi.wei.dao;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.wei.model.WxArticleNews;

public interface WxArticleNewsMapper extends BaseMapper<WxArticleNews>{
	public int deleteByArticleId(Long articleId);
	public List<WxArticleNews> queryAllList(Map<String, Object> params);
}
