package com.huayi.wei.handler;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.wei.service.WeiUserService;
import com.huayi.wei.model.WeiUser;
import com.github.binarywang.java.emoji.EmojiConverter;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@Service
public class UpdateHandler implements WxMpMessageHandler{
	
	@Autowired
	private WeiUserService weiUserService;
	
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		String openId = wxMessage.getFromUser();
		WeiUser weiUser = weiUserService.queryByOpenId(openId);
		//获得用户信息
		String lang = "zh_CN"; //语言		
		//数据库用户不存在
		if(weiUser == null){
			weiUser = new WeiUser();
			WxMpUser wxMpUser = wxMpService.getUserService().userInfo(openId,lang);
			WxUserToUser.createWeiUser(weiUser,wxMpUser);
		}
		if(weiUser.getId() == null) {
			weiUserService.insert(weiUser);
		}else if(weiUser.getSubscribe() == false){
			weiUser.setSubscribe(true);
			weiUserService.updateDB(weiUser);
		}
		return null;
	}
}
