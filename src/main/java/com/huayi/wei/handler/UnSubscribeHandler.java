package com.huayi.wei.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.wei.service.WeiUserService;
import com.huayi.wei.model.WeiUser;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
/**
 * 
* @ClassName: UnSubscribeHandler 
* @Description: 取消关注处理
* @author panlei
* @date 2017年8月8日 下午7:48:47 
*
 */
@Service
public class UnSubscribeHandler implements WxMpMessageHandler{
	private Logger logger = LoggerFactory.getLogger(UnSubscribeHandler.class);
	
	@Autowired
	private WeiUserService weiUserService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		logger.info("取消关注用户 OPENID: " + wxMessage.getFromUser());
		String openId = wxMessage.getFromUser();
		WeiUser weiUser = weiUserService.queryByOpenId(openId);
		weiUser.setSubscribe(false);
		weiUserService.updateDB(weiUser);
		return null;
	}

}
