package com.huayi.wei.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.wei.dao.WxArticleMapper;
import com.huayi.wei.model.WxArticle;
import com.huayi.wei.service.WxArticleService;

@Service
public class WxArticleServiceImpl extends BaseServiceImpl<WxArticle> implements WxArticleService{
	
	@Autowired
	private WxArticleMapper wxArticleMapper;

	@Override
	public PageInfo<WxArticle> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WxArticle> getMapper() {
		return wxArticleMapper;
	}

}
