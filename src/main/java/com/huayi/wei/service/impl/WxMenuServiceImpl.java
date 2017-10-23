package com.huayi.wei.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.wei.service.WxMenuService;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.wei.dao.WxMenuMapper;
import com.huayi.common.exception.ServiceException;
import com.huayi.wei.model.WxMenu;
import com.github.pagehelper.PageInfo;
@Service
public class WxMenuServiceImpl extends BaseServiceImpl<WxMenu> implements WxMenuService{
	
	@Autowired
	private WxMenuMapper wxMenuMapper;

	@Override
	public PageInfo<WxMenu> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WxMenu> getMapper() {
		return wxMenuMapper;
	}

}
