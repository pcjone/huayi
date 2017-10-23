package com.huayi.${sys_name}.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.${sys_name}.service.${table_name}Service;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.${sys_name}.dao.${table_name}Mapper;
import com.huayi.common.exception.ServiceException;
import com.huayi.${sys_name}.model.${table_name};
import com.github.pagehelper.PageInfo;
@Service
public class ${table_name}ServiceImpl extends BaseServiceImpl<${table_name}> implements ${table_name}Service{
	
	@Autowired
	private ${table_name}Mapper ${table_name_small}Mapper;

	@Override
	public PageInfo<${table_name}> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<${table_name}> getMapper() {
		return ${table_name_small}Mapper;
	}

}
