package com.huayi.tags;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.common.Constants;
import com.huayi.common.freemarker.SuperBaseTag;
import com.huayi.wei.service.WxMenuService;

@Service
public class WxMenuTag extends SuperBaseTag{
	
	@Autowired
	private WxMenuService wxMenuService;

	@Override
	protected Object result(Map<String, Object> params) {
		String excludeStr = null;
		Map<String, Object> searchParams = new HashMap<String, Object>();
		excludeStr = getString(params, "value");
		searchParams.put("appId", excludeStr);
		searchParams.put("parentId", Constants.PERMISSION_ZERO);
		searchParams.put("enable", Constants.ENABLE_NO);
		return wxMenuService.queryAllList(searchParams);
	}

}
