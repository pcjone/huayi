package com.huayi.sys.dao;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseMapper;
import com.huayi.sys.model.SysDic;

public interface SysDicMapper extends BaseMapper<SysDic>{

	/**
	 * 
	* @Title: queryListByCodeText 
	* @Description: 根据key查询value
	* @param @param params
	* @param @return     
	* @return List<SysDic>    
	* @throws
	 */
	List<SysDic> queryListByCategory(Map<String,Object> params);
	
}
