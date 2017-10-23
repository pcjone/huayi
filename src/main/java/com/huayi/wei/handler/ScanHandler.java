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
* @ClassName: ScanHandler 
* @Description: 扫码事件处理
* @author panlei
* @date 2017年10月16日 下午1:59:58 
*
 */
@Service
public class ScanHandler implements WxMpMessageHandler{
	
	private Logger logger = LoggerFactory.getLogger(ScanHandler.class);
	
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		logger.info("扫码事件处理");
		return null;
	}

}
