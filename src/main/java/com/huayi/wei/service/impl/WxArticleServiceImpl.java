package com.huayi.wei.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.wei.dao.WxArticleMapper;
import com.huayi.wei.dao.WxArticleNewsMapper;
import com.huayi.wei.model.WxArticle;
import com.huayi.wei.model.WxArticleNews;
import com.huayi.wei.service.WxArticleNewsService;
import com.huayi.wei.service.WxArticleService;

@Service
public class WxArticleServiceImpl extends BaseServiceImpl<WxArticle> implements WxArticleService{
	
	@Autowired
	private WxArticleMapper wxArticleMapper;
	
	@Autowired
	private WxArticleNewsService wxArticleNewsService;

	@Override
	public PageInfo<WxArticle> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WxArticle> getMapper() {
		return wxArticleMapper;
	}

	@Override
	public void newsSet(Long[] ids, Long id) {
		wxArticleNewsService.deleteByArticleId(id);
		for(Long newsId : ids) {
			WxArticleNews news = new WxArticleNews();
			news.setArticleId(id);
			news.setNewsId(newsId);
			wxArticleNewsService.insert(news);
		}
		
	}

	@Override
	public List<WxArticle> queryAllList(Map<String, Object> params) {
		return wxArticleMapper.queryAllList(params);
	}

}
