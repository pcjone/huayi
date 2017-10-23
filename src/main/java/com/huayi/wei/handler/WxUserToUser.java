package com.huayi.wei.handler;

import java.util.Date;

import com.huayi.wei.model.WeiUser;
import com.github.binarywang.java.emoji.EmojiConverter;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

public class WxUserToUser {
	/**
	 * 
	* @Title: createWeiUser 
	* @Description: 组装微信用户
	* @param @param weiUser
	* @param @param wxMpUser
	* @param @return     
	* @return WeiUser    
	* @throws
	 */
	public static WeiUser createWeiUser(WeiUser weiUser,WxMpUser wxMpUser) {
		weiUser.setOpenId(wxMpUser.getOpenId());
		weiUser.setNickname(EmojiConverter.getInstance().toUnicode(wxMpUser.getNickname()));
		weiUser.setHeadimgurl(wxMpUser.getHeadImgUrl());
		weiUser.setSex(wxMpUser.getSex());
		weiUser.setSexId(wxMpUser.getSexId());
		weiUser.setCity(wxMpUser.getCity());
		weiUser.setCountry(wxMpUser.getCountry());
		weiUser.setProvince(wxMpUser.getProvince());
		weiUser.setLanguage(wxMpUser.getLanguage());
		weiUser.setUnionId(wxMpUser.getUnionId());
		weiUser.setSubscribeTime(new Date(wxMpUser.getSubscribeTime()*1000));
		weiUser.setSubscribe(wxMpUser.getSubscribe());
		weiUser.setGroupId(wxMpUser.getGroupId());
		return weiUser;
	}
}
