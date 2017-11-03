package com.huayi.wei.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.wei.model.WxMenu;

public interface WxMenuService extends BaseService<WxMenu>{
	public List<WxMenu> queryAllList(Map<String,Object> params);
	public void publish(String appId);
}
