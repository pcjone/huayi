package com.huayi.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerListener implements ServletContextListener{
	
	protected final Logger logger = LogManager.getLogger(ServerListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		logger.info("contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		logger.info("=================================");
		logger.info("系统[{}]启动成功!!!", contextEvent.getServletContext().getServletContextName());
		logger.info("=================================");
	}

}
