package com.huayi.common.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
* @ClassName: ChooseDataSource 
* @Description: 获取数据源
* @author panlei
* @date 2017年10月17日 下午4:43:43 
*
 */
public class ChooseDataSource extends AbstractRoutingDataSource {
	public static Map<String, List<String>> METHODTYPE = new HashMap<String, List<String>>();

	// 获取数据源名称
	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getDataSource();
	}

	// 设置方法名前缀对应的数据源
	public void setMethodType(Map<String, String> map) {
		for (String key : map.keySet()) {
			List<String> v = new ArrayList<String>();
			String[] types = map.get(key).split(",");
			for (String type : types) {
				if (StringUtils.isNotBlank(type)) {
					v.add(type);
				}
			}
			METHODTYPE.put(key, v);
		}
	}
}
