package com.huayi.wei.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * 
* @ClassName: LogHandler 
* @Description: 消息日志
* @author panlei
* @date 2017年10月16日 下午2:02:36 
*
 */
@Service("logHandler")
public class LogHandler implements WxMpMessageHandler {
	
	private Logger logger = LoggerFactory.getLogger(LogHandler.class);
	
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
			Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager)
			throws WxErrorException {
		logger.info("=====================日志打印======================");
		logger.info(wxMpXmlMessage.toString());
		logger.info("==================================================");
	    return null;
	}

}
