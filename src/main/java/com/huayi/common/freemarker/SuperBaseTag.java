package com.huayi.common.freemarker;

import java.util.Map;

import com.huayi.common.util.StringUtils;
/**
 * 自定义标签的父类。
* @ClassName: SuperCustomTag 
* @Description: TODO
* @author panlei
* @date 2017年8月21日 下午2:25:16 
*
 */
public abstract class SuperBaseTag {
	
	/**
	 * 本方法采用多态集成的方式，然后用父类接收，用父类调用子类的 {@link result(...)} 方法。
	 * @param params
	 * @return
	 */
	protected abstract Object result(Map<String,Object> params);
	
	/**
	 * 直接强转报错，需要用Object过度一下
	 * @param e
	 * @return
	 */
	protected Long getLong(Map<String,Object> params,String key){
		Object i = params.get(key);
		return StringUtils.isBlank(i)?null:new Long(i.toString());
	}
	protected String getString(Map<String,Object> params,String key){
		Object i = params.get(key);
		return StringUtils.isBlank(i)?null:i.toString();
	}
	protected Integer getInt(Map<String,Object> params,String key){
		Object i = params.get(key);
		return StringUtils.isBlank(i)?null:Integer.parseInt(i.toString());
	}
}
