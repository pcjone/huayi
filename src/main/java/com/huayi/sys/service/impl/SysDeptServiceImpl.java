package com.huayi.sys.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.huayi.common.Constants;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.common.exception.ServiceException;
import com.huayi.sys.dao.SysDeptMapper;
import com.huayi.sys.model.SysDept;
import com.huayi.sys.model.expand.SysDeptExpand;
import com.huayi.sys.service.SysDeptService;

@Service
public class SysDeptServiceImpl extends BaseServiceImpl<SysDept> implements SysDeptService{
	
	@Autowired
	private SysDeptMapper sysDeptMapper;

	@Override
	public PageInfo<SysDept> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<SysDept> getMapper() {
		return sysDeptMapper;
	}

	@Override
	public List<SysDeptExpand> queryListDeptTree(Map<String, Object> params) {
		List<SysDept> depts = sysDeptMapper.queryForList(params);
		return getChild(depts,Constants.PERMISSION_ZERO);
	}
	
	public List<SysDeptExpand> getChild(List<SysDept> sysDeptLists,Long parentId){
		List<SysDeptExpand> returnSysDept = new ArrayList<SysDeptExpand>();
		//循环查询parentId相等的
		for(SysDept tree : sysDeptLists) {
			SysDeptExpand treeExpand = new SysDeptExpand();
			try {
				BeanUtils.copyProperties(treeExpand,tree);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			if(tree.getParentId().equals(parentId)) {
				List<SysDeptExpand> child = getChild(sysDeptLists,tree.getId());			
				if(child != null && child.size()>0) {
					treeExpand.setChildSysDept(child);
					treeExpand.setHasChild(true);
				}else {
					treeExpand.setHasChild(false);
				}
				returnSysDept.add(treeExpand);
			}
		}
		return returnSysDept;
	}

}
