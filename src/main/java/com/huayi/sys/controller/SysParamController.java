package com.huayi.sys.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.huayi.common.base.BaseController;
import com.huayi.common.util.HtmlUtil;
import com.huayi.common.util.Request2ModelUtil;
import com.huayi.common.util.WebUtil;
import com.huayi.sys.model.SysParam;
import com.huayi.sys.service.SysParamService;
/**
 * 
* @ClassName: SysDicController 
* @Description: 系统参数控制器
* @author panlei
* @date 2017年7月26日 下午5:41:17 
*
 */
@Controller
@RequestMapping("param")
public class SysParamController extends BaseController{
	@Autowired
	private SysParamService sysParamService;
	/**
	 * 
	* @Title: list 
	* @Description: 跳转列表页
	* @param @param request
	* @param @param response
	* @param @return     
	* @return Object    
	* @throws
	 */
	@RequiresPermissions("sys.param.list")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "系统参数");
		return forword("sys/param/param",context);
	}
	/**
	 * 
	* @Title: dataList 
	* @Description: 分页查询
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("sys.param.dataList")
	@RequestMapping(value="/dataList",method = RequestMethod.POST)
	public void dataList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<SysParam> pageList = sysParamService.query(params);
		HtmlUtil.writerJson(response,pageList);
	}
	/**
	 * 
	* @Title: cancel 
	* @Description: 逻辑删除
	* @param @param ids
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("sys.param.cancel")
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public void cancel(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		sysParamService.cancelDBAndCache(ids, getCurrUser());
		sendSuccessMessage(response,"删除成功");
	}
	
	/**
	 * 
	* @Title: detete 
	* @Description: 物理删除
	* @param @param ids
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequiresPermissions("sys.param.delete")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void detete(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		sysParamService.deleteDBAndCache(ids);
		sendSuccessMessage(response,"删除成功");
	}
	
	/**
	 * 
	* @Title: queryById 
	* @Description: 根据id查询
	* @param @param id
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value="/queryById",method = RequestMethod.POST)
	public void queryById(Long id,HttpServletRequest request, HttpServletResponse response) {
		SysParam record = sysParamService.queryCacheById(id);
		HtmlUtil.writerJson(response,record);
	}
	/**
	 * 
	* @Title: save 
	* @Description: 新增或更新
	* @param @param request
	* @param @param response     
	* @return void    
	* @throws
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response) {
		SysParam record = Request2ModelUtil.covert(SysParam.class,request);
		if(record != null) {
			if(record.getId() == null) {
				Map<String,Object> searchParams = Maps.newConcurrentMap();
				searchParams.put("paramKey", record.getParamKey());
				List<SysParam> checkList = sysParamService.validateSysParam(searchParams);
				if(checkList!=null && checkList.size()>0) {
					sendFailureMessage(response,"新增失败,参数名称已存在！");
					return;
				}
				record.setCreateBy(getCurrUser());
				sysParamService.insert(record);
				sendSuccessMessage(response,"新增成功");
			}else {
				record.setUpdateBy(getCurrUser());
				sysParamService.updateDBAndCache(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
}
