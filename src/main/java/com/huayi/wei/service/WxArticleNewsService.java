package com.huayi.wei.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.wei.model.WxArticleNews;

public interface WxArticleNewsService extends BaseService<WxArticleNews>{
	
	public int deleteByArticleId(Long articleId);
	
	public List<WxArticleNews> queryAllList(Map<String,Object> params);

}
