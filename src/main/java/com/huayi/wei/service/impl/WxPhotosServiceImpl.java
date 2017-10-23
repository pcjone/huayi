package com.huayi.wei.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.wei.service.WxPhotosService;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.wei.dao.WxPhotosMapper;
import com.huayi.wei.model.WxPhotos;
import com.github.pagehelper.PageInfo;
@Service
public class WxPhotosServiceImpl extends BaseServiceImpl<WxPhotos> implements WxPhotosService{
	
	@Autowired
	private WxPhotosMapper wxPhotosMapper;

	@Override
	public PageInfo<WxPhotos> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WxPhotos> getMapper() {
		return wxPhotosMapper;
	}

}
