package com.huayi.common.datasource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
* @ClassName: DataSourceAspect 
* @Description: 切换数据源(不同方法调用不同数据源)
* @author panlei
* @date 2017年10月17日 下午4:48:12 
*
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
	private final Logger logger = LogManager.getLogger();

	@Pointcut("execution(* com.huayi.*.service..*.*(..))")
	public void aspect() {
	}

	/**
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 */
	@Before("aspect()")
	public void before(JoinPoint point) {
		String className = point.getTarget().getClass().getName();
		String method = point.getSignature().getName();
		logger.info(className + "." + method + "(" + StringUtils.join(point.getArgs(), ",") + ")");
		try {
			L: for (String key : ChooseDataSource.METHODTYPE.keySet()) {
				for (String type : ChooseDataSource.METHODTYPE.get(key)) {
					if (method.startsWith(type)) {
						logger.info(key);
						DataSourceHolder.setDataSource(key);
						break L;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			DataSourceHolder.setDataSource("write");
		}
	}

	@After("aspect()")
	public void after(JoinPoint point) {
		DataSourceHolder.clear();
	}
}
