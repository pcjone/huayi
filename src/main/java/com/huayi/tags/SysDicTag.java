package com.huayi.tags;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.common.Constants;
import com.huayi.common.freemarker.SuperBaseTag;
import com.huayi.sys.service.SysDicService;
/**
 * 
* @ClassName: SysDicTag 
* @Description: 获取字典值标签
* @author panlei
* @date 2017年8月21日 下午5:19:09 
*
 */
@Service
public class SysDicTag extends SuperBaseTag {

	@Autowired
	private SysDicService sysDicService;

	@Override
	public Object result(Map<String,Object> params) {
		String excludeStr = null;
		Map<String, Object> searchParams = new HashMap<String, Object>();
		excludeStr = getString(params, "value");
		searchParams.put("category", excludeStr);
		searchParams.put("enable", Constants.ENABLE_NO);
		return sysDicService.queryListByCategory(searchParams);
	}

}
