package com.huayi.wei.dao;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.wei.model.WxMenu;

public interface WxMenuMapper extends BaseMapper<WxMenu>{
	public List<WxMenu> queryAllList(Map<String, Object> params);
}
