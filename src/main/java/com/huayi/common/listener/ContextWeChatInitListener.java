package com.huayi.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;

/**
 * 
 * @ClassName: ContextWeChatInitListener
 * @Description: 监听ServletContext对象
 * @author panlei 446756738@qq.com
 * @date 2017年6月29日 下午3:14:46
 *
 */
public class ContextWeChatInitListener implements ServletContextListener {
	
	private Logger logger = Logger.getLogger(ContextWeChatInitListener.class);
	
	// 获取spring注入的bean对象
	private WebApplicationContext springContext;
	// 获取上下文
	private ServletContext context;

	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		springContext = WebApplicationContextUtils.getWebApplicationContext(context);
		if (springContext != null) {
			WxMpMessageRouter wxMpMessageRouter = (WxMpMessageRouter) springContext.getBean("wxMpMessageRouter");
			// 日志
			WxMpMessageHandler logHandler = (WxMpMessageHandler)springContext.getBean("logHandler");
			// 关注事件
			WxMpMessageHandler subscribeHandler = (WxMpMessageHandler)springContext.getBean("subscribeHandler");
			// 关注更新
			WxMpMessageHandler updateHandler = (WxMpMessageHandler)springContext.getBean("updateHandler");
			// 取消关注事件
			WxMpMessageHandler unSubscribeHandler = (WxMpMessageHandler)springContext.getBean("unSubscribeHandler");
			// 空处理
			WxMpMessageHandler nullHandler = (WxMpMessageHandler)springContext.getBean("nullHandler");
			// 地理位置事件
			WxMpMessageHandler locationHandler = (WxMpMessageHandler)springContext.getBean("locationHandler");
			// 接收客服会话管理事件
			WxMpMessageHandler kfSessionHandler = (WxMpMessageHandler)springContext.getBean("kfSessionHandler");
			// 自定义菜单事件
			WxMpMessageHandler menuHandler = (WxMpMessageHandler)springContext.getBean("menuHandler");
			// 门店审核事件
			WxMpMessageHandler storeCheckNotifyHandler = (WxMpMessageHandler)springContext.getBean("storeCheckNotifyHandler");
			// 扫码事件
			WxMpMessageHandler scanHandler = (WxMpMessageHandler)springContext.getBean("scanHandler");
			// 默认
			WxMpMessageHandler msgHandler = (WxMpMessageHandler)springContext.getBean("msgHandler");
			// 记录所有事件的日志 （异步执行）
			wxMpMessageRouter.rule().handler(logHandler).next();
			// 接收客服会话管理事件
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
				.event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION).handler(kfSessionHandler).end();
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
				.event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION).handler(kfSessionHandler).end();
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
				.event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION).handler(kfSessionHandler).end();
			
			// 门店审核事件
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
				.event(WxMpEventConstants.POI_CHECK_NOTIFY).handler(storeCheckNotifyHandler).end();
			
			// 自定义菜单事件
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
            		.event(WxConsts.BUTTON_CLICK).handler(menuHandler).end();
			
			// 点击菜单连接事件
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
				.event(WxConsts.BUTTON_VIEW).handler(nullHandler).end();
	        
			// 关注事件
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
				.event(WxConsts.EVT_SUBSCRIBE).handler(subscribeHandler).end();
			
			// 取消关注事件
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
				.event(WxConsts.EVT_UNSUBSCRIBE).handler(unSubscribeHandler).end();
			
			// 上报地理位置事件
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
	        		.event(WxConsts.EVT_LOCATION).handler(locationHandler).end();
			
			// 接收地理位置消息
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_LOCATION)
	        		.handler(locationHandler).end();
			
			 // 扫码事件
			wxMpMessageRouter.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
	                .event(WxConsts.EVT_SCAN).handler(scanHandler).end();
			
			// 默认
			wxMpMessageRouter.rule().async(false).handler(msgHandler).end();
			logger.info("log>>>初始化微信基础配置。。。完成");
		} else {
			logger.info("no beans has initialized");
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		logger.info("-----end---------");
	}

}
