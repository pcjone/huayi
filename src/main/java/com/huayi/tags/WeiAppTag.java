package com.huayi.tags;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.common.Constants;
import com.huayi.common.freemarker.SuperBaseTag;
import com.huayi.wei.service.WxAppService;

@Service
public class WeiAppTag extends SuperBaseTag{
	
	@Autowired
	private WxAppService wxAppService;

	@Override
	protected Object result(Map params) {
		String excludeStr = null;
		Map<String, Object> searchParams = new HashMap<String, Object>();
		excludeStr = getString(params, "value");
		searchParams.put("appId", excludeStr);
		searchParams.put("enable", Constants.ENABLE_NO);
		return wxAppService.queryAll(searchParams);
	}

}
