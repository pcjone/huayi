package com.huayi.wei.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.huayi.common.Constants;
import com.huayi.common.base.BaseController;
import com.huayi.common.util.FileUploadUtil;
import com.huayi.common.util.HtmlUtil;
import com.huayi.common.util.Request2ModelUtil;
import com.huayi.common.util.WebUtil;
import com.huayi.sys.model.SysParam;
import com.huayi.sys.service.SysParamService;
import com.huayi.wei.model.WxNews;
import com.huayi.wei.model.WxPhotos;
import com.huayi.wei.service.WxNewsService;
import com.huayi.wei.service.WxPhotosService;

@Controller
@RequestMapping("wxNews")
public class WxNewsController extends BaseController{
	@Autowired
	private WxNewsService wxNewsService;
	@Autowired
	private SysParamService sysParamService;
	@Autowired
	private WxPhotosService wxPhotosService;
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
	@RequiresPermissions("wei.wxNews.list")
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> context = getRootMap();
		context.put("title", "微信图文");
		return forword("wei/wxNews/wxNews",context);
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
	@RequiresPermissions("wei.wxNews.dataList")
	@RequestMapping(value="/dataList",method = RequestMethod.POST)
	public void dataList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> params = WebUtil.getParameterMap(request);
		PageInfo<WxNews> pageList = wxNewsService.query(params);
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
	@RequiresPermissions("wei.wxNews.cancel")
	@RequestMapping(value="/cancel",method = RequestMethod.POST)
	public void cancel(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		wxNewsService.cancelDBAndCache(ids, getCurrUser());
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
	@RequiresPermissions("wei.wxNews.delete")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public void detete(Long[] ids,HttpServletRequest request, HttpServletResponse response) {
		wxNewsService.deleteDBAndCache(ids);
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
		WxNews record = wxNewsService.queryCacheById(id);
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
	public void save(HttpServletRequest request,@RequestParam(value="image",required = false) MultipartFile multipartFile,
			HttpServletResponse response) {
		WxNews record = Request2ModelUtil.covert(WxNews.class,request);
		if(record != null) {
			if(multipartFile != null && !multipartFile.isEmpty()) {
				SysParam param  = sysParamService.queryByKey(Constants.WXNEWS_PHOTO_PATH);
				String realPath = "";
				if(param == null) {
					realPath = Constants.DEFAULT_PHOTO_PATH;
				}else {
					realPath  = param.getParamValue();
				}
				String path = FileUploadUtil.doUpload(multipartFile, realPath);
				WxPhotos photo = new WxPhotos();
				photo.setPath(path);
				photo.setTableName("wx_news");
				photo.setCreateBy(getCurrUser());
				photo = wxPhotosService.insert(photo);
				record.setPicUrl(photo.getId());
			}
			if(record.getId() == null) {
				record.setCreateBy(getCurrUser());
				wxNewsService.insert(record);
				sendSuccessMessage(response,"新增成功");
			}else {
				record.setUpdateBy(getCurrUser());
				wxNewsService.updateDB(record);
				sendSuccessMessage(response,"更新成功");
			}
		}
	}
}
