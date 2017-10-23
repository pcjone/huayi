package com.huayi.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageInfo;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.sys.dao.SysParamMapper;
import com.huayi.sys.model.SysParam;
import com.huayi.sys.service.SysParamService;
/**
 * 
* @ClassName: SysParamServiceImpl 
* @Description: 参数服务
* @author panlei
* @date 2017年7月27日 下午8:46:59 
*
 */
@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParam> implements SysParamService{

	@Autowired
	private SysParamMapper sysParamMapper;
	
	@Override
	public PageInfo<SysParam> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<SysParam> getMapper() {
		return sysParamMapper;
	}

	@Override
	public List<SysParam> validateSysParam(Map<String, Object> params) {
		return sysParamMapper.querySysParam(params);
	}

	@Override
	public SysParam queryByKey(String paramKey) {
		return sysParamMapper.queryByKey(paramKey);
	}

}
