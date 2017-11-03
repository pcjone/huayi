package com.huayi.wei.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.wei.dao.WxArticleNewsMapper;
import com.huayi.wei.model.WxArticleNews;
import com.huayi.wei.service.WxArticleNewsService;

@Service
public class WxArticleNewsServiceImpl extends BaseServiceImpl<WxArticleNews> implements WxArticleNewsService{

	@Autowired
	private WxArticleNewsMapper wxArticleNewsMapper;
	
	@Override
	public PageInfo<WxArticleNews> query(Map<String, Object> params) throws ServiceException {
		return null;
	}

	@Override
	protected BaseMapper<WxArticleNews> getMapper() {
		return wxArticleNewsMapper;
	}

	@Override
	public int deleteByArticleId(Long articleId) {
		return wxArticleNewsMapper.deleteByArticleId(articleId);
	}

	@Override
	public List<WxArticleNews> queryAllList(Map<String, Object> params) {
		return wxArticleNewsMapper.queryAllList(params);
	}

}
