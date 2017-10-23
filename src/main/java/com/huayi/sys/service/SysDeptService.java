package com.huayi.sys.service;

import java.util.List;
import java.util.Map;

import com.huayi.common.base.BaseService;
import com.huayi.sys.model.SysDept;
import com.huayi.sys.model.expand.SysDeptExpand;

public interface SysDeptService extends BaseService<SysDept>{
	
	public List<SysDeptExpand> queryListDeptTree(Map<String,Object> params);

}
