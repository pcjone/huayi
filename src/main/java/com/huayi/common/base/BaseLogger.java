package com.huayi.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
* @ClassName: BaseLogger
* @Description: Log基类,所有的类默认继承此类,可以直接使用 logger 记录日志
* @author panlei
* @date 2017年10月18日 上午10:11:30 
*
 */
public class BaseLogger {
	public   Logger logger = LoggerFactory.getLogger(getClass());
}
