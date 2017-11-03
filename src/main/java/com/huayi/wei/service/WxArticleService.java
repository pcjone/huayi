package com.huayi.wei.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.wei.model.WxArticle;

public interface WxArticleService extends BaseService<WxArticle>{
	
	public void newsSet(Long[] ids, Long id);
	
	public List<WxArticle> queryAllList(Map<String,Object> params);

}
