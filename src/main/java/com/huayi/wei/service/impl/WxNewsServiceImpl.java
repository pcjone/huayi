package com.huayi.wei.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.wei.service.WxNewsService;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.wei.dao.WxNewsMapper;
import com.huayi.wei.model.WxNews;
import com.github.pagehelper.PageInfo;

@Service
public class WxNewsServiceImpl extends BaseServiceImpl<WxNews> implements WxNewsService{
	
	@Autowired
	private WxNewsMapper wxNewsMapper;

	@Override
	public PageInfo<WxNews> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WxNews> getMapper() {
		return wxNewsMapper;
	}

	@Override
	public List<WxNews> queryAllList(Map<String, Object> params) {
		return wxNewsMapper.queryAllList(params);
	}

}
