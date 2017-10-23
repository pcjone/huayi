package com.huayi.sys.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.sys.model.SysDic;


public interface SysDicService extends BaseService<SysDic>{

	public List<SysDic> queryListByCategory(Map<String,Object> params);

	public List<SysDic> validateSysDic(Map<String,Object> params);
}
