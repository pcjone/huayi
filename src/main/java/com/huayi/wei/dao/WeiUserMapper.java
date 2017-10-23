package com.huayi.wei.dao;

import com.huayi.common.base.BaseMapper;
import com.huayi.wei.model.WeiUser;

public interface WeiUserMapper extends BaseMapper<WeiUser>{
	public WeiUser queryByOpenId(String openId);
}
