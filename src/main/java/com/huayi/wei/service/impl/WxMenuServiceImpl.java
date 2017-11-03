package com.huayi.wei.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.wei.service.WxMenuService;

import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

import com.huayi.common.Constants;
import com.huayi.common.base.BaseMapper;
import com.huayi.common.base.BaseServiceImpl;
import com.huayi.wei.dao.WxMenuMapper;
import com.huayi.common.exception.ServiceException;
import com.huayi.wei.model.WxMenu;
import com.github.pagehelper.PageInfo;
@Service
public class WxMenuServiceImpl extends BaseServiceImpl<WxMenu> implements WxMenuService{
	
	@Autowired
	private WxMenuMapper wxMenuMapper;
	
	@Autowired
	private WxMpService wxMpService;

	@Override
	public PageInfo<WxMenu> query(Map<String, Object> params) throws ServiceException {
		this.startPage(params);
		return getPageByDB(getMapper().queryForList(params));
	}

	@Override
	protected BaseMapper<WxMenu> getMapper() {
		return wxMenuMapper;
	}

	@Override
	public void publish(String appId) {
		try {
			me.chanjar.weixin.common.bean.menu.WxMenu menu = new me.chanjar.weixin.common.bean.menu.WxMenu();
			List<WxMenuButton> buttons = new ArrayList<WxMenuButton>();
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("appId", appId);
			params.put("parentId", Constants.PERMISSION_ZERO);
			params.put("enable", Constants.ENABLE_NO);
			List<WxMenu> menus = wxMenuMapper.queryAllList(params);
			for(WxMenu wx : menus) {
				WxMenuButton wxMenuButton = new WxMenuButton();
				wxMenuButton.setType(wx.getType());
				wxMenuButton.setKey(wx.getKeyValue());
				wxMenuButton.setUrl(wx.getLink());
				wxMenuButton.setName(wx.getText());
				List<WxMenuButton> subButtons = new ArrayList<WxMenuButton>();
				params.put("parentId", wx.getId());
				List<WxMenu> subMenus = wxMenuMapper.queryAllList(params);
				for(WxMenu sub : subMenus) {
					WxMenuButton subWxMenuButton = new WxMenuButton();
					subWxMenuButton.setType(sub.getType());
					subWxMenuButton.setKey(sub.getKeyValue());
					subWxMenuButton.setUrl(sub.getLink());
					subWxMenuButton.setName(sub.getText());
					subButtons.add(subWxMenuButton);
				}
				wxMenuButton.setSubButtons(subButtons);
				buttons.add(wxMenuButton);
			}
			menu.setButtons(buttons);
			String result = wxMpService.getMenuService().menuCreate(menu);
			logger.info("[菜单发布：]："+result);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<WxMenu> queryAllList(Map<String, Object> params) {
		return wxMenuMapper.queryAllList(params);
	}

}
