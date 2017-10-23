package com.huayi.wei.handler;

import java.util.Date;
import java.util.Map;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.wei.model.WeiUser;
import com.huayi.wei.service.WeiUserService;
/**
 * 
* @ClassName: SubscribeHandler 
* @Description: 关注事件
* @author panlei
* @date 2017年10月16日 下午2:06:58 
*
 */
@Service
public class SubscribeHandler implements WxMpMessageHandler{
	
	private Logger logger = LoggerFactory.getLogger(SubscribeHandler.class);
	
	@Autowired
	private WeiUserService weiUserService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());
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
		
		WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        if (responseResult != null) {
            return responseResult;
        }
		
		WxMpXmlOutTextMessage message = WxMpXmlOutMessage.TEXT()
				.content("欢迎关注").fromUser(wxMessage.getToUser())
				.toUser(wxMessage.getFromUser()).build();
		return message;
	}
	
	/**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }
}
