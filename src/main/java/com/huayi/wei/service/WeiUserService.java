package com.huayi.wei.service;

import com.huayi.common.base.BaseService;
import com.huayi.wei.model.WeiUser;

public interface WeiUserService extends BaseService<WeiUser>{
	public WeiUser queryByOpenId(String openId);
}
