package com.huayi.wei.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huayi.wei.model.WeiUser;
import com.huayi.wei.service.WeiUserService;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
/**
 * 
* @ClassName: LocationHandler 
* @Description: 地理位置处理
* @author panlei
* @date 2017年10月13日 下午3:04:54 
*
 */
@Service
public class LocationHandler implements WxMpMessageHandler{
	
	private Logger logger = LoggerFactory.getLogger(LocationHandler.class);
	
	@Autowired
	private WeiUserService weiUserService;

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		logger.info("地理位置上报处理");
		if (wxMessage.getMsgType().equals(WxConsts.XML_MSG_LOCATION)) {
			logger.info("接收处理用户发送的地理位置消息");
            //TODO 接收处理用户发送的地理位置消息
            try {
                String content = "感谢反馈，您的的地理位置已收到！";
                WeiUser user = weiUserService.queryByOpenId(wxMessage.getFromUser());
                user.setLatitude(wxMessage.getLocationX());
                user.setLongitude(wxMessage.getLocationY());
                user.setPrecisions(wxMessage.getPrecision());
                user.setLabel(wxMessage.getLabel());
                weiUserService.updateDB(user);
                WxMpXmlOutTextMessage message = WxMpXmlOutMessage.TEXT()
        				.content(content).fromUser(wxMessage.getToUser())
        				.toUser(wxMessage.getFromUser()).build();
        		return message;
            } catch (Exception e) {
                this.logger.error("位置消息接收处理失败", e);
                return null;
            }
        }

        //上报地理位置事件
        this.logger.info("\n上报地理位置 。。。 ");
        this.logger.info("\n纬度 : " + wxMessage.getLatitude());
        this.logger.info("\n经度 : " + wxMessage.getLongitude());
        this.logger.info("\n精度 : " + String.valueOf(wxMessage.getPrecision()));

        //TODO  可以将用户地理位置信息保存到本地数据库，以便以后使用
        return null;
	}

}
