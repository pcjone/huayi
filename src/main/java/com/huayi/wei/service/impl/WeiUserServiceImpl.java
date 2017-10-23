package com.huayi.wei.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.wei.dao.WeiUserMapper;
import com.huayi.wei.model.WeiUser;
import com.huayi.wei.service.WeiUserService;

@Service
public class WeiUserServiceImpl extends BaseServiceImpl<WeiUser> implements WeiUserService{

	@Autowired
	private WeiUserMapper weiUserMapper;
	
	@Override
	public PageInfo<WeiUser> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WeiUser> getMapper() {
		return weiUserMapper;
	}

	@Override
	public WeiUser queryByOpenId(String openId) {
		return weiUserMapper.queryByOpenId(openId);
	}
	
	@Transactional
	@Override
	public WeiUser insert(WeiUser weiUser) {
		getMapper().insert(weiUser);
		return weiUser;
	}
	
	@Transactional
	@Override
	public WeiUser updateDB(WeiUser weiUser) {
		try {
			if (weiUser.getId() == null) {
				getMapper().insert(weiUser);
			} else {
				getMapper().updateByPrimaryKey(weiUser);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return weiUser;
	}

}
