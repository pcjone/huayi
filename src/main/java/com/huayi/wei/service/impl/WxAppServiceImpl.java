package com.huayi.wei.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.wei.service.WxAppService;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.wei.dao.WxAppMapper;
import com.huayi.common.exception.ServiceException;
import com.huayi.wei.model.WxApp;
import com.github.pagehelper.PageInfo;
@Service
public class WxAppServiceImpl extends BaseServiceImpl<WxApp> implements WxAppService{
	
	@Autowired
	private WxAppMapper wxAppMapper;

	@Override
	public PageInfo<WxApp> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WxApp> getMapper() {
		return wxAppMapper;
	}

	@Override
	public List<WxApp> queryAll(Map<String, Object> params) {
		return null;
	}

}
