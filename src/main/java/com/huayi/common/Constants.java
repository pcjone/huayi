package com.huayi.common;
/**
 * 
* @ClassName: Constants 
* @Description: 常量表
* @author panlei
* @date 2017年10月17日 下午5:51:54 
*
 */
public class Constants {
	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String Exception_Head  = "系统出错了！";
	/**缓存命名空间*/
	public static final String CACHE_NAMESPACE = "huayi";	
	/** 当前用户信息 */
	public static final String CURRENT_USER = "CURRENT_USER";	
	/** 在线用户数量 */
	public static final String ALLUSER_NUMBER = "ALLUSER_NUMBER";
	/** 登录用户数量 */
	public static final String USER_NUMBER = "USER_NUMBER";
	/** 上次请求地址 */
	public static final String PREREQUEST = "PREREQUEST";
	/** 上次请求时间 */
	public static final String PREREQUEST_TIME = "PREREQUEST_TIME";
	/** 非法请求次数 */
	public static final String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
	public static final String SHIRO_SESSION_CACHE = "shiro_huayi_session";
	
	/**是否锁定*/
	public static final Integer ENABLE_YES = 1;//锁定
	public static final Integer ENABLE_NO = 0;//未锁定
	
	/*权限顶级parentId*/
	public static final Long PERMISSION_ZERO = 0l;
	
	/*是否超级管理员*/
	public final static Long SUPER_ADMIN_YES = 1l;//超级管理员
	public final static Long SUPER_ADMIN_NO = 0l;//非超级管理员
	
	/**
	 * freemarke统一标签
	 */
	public final static String TARGET = "target";
	public final static String OUT_TAG_NAME = "outTagName";
	
	/*
	 * 登录异常
	 */
	public static final String USER_NOT_EXIST = "账户不存在！";
	public static final String USER_WRONG = "用户名或密码错误！";
	public static final String ACCOUNT_IS_NULL = "账号不能为空！";
	public static final String PASSWORD_IS_NULL = "请输入密码！";
	public static final String SUCCESS  ="登录成功";
	public static final String FAILURE  ="登录失败";
	public static final String USER_ENABLED ="账号被锁定，请联系管理员";
	
	/**
	 * 缓存key值前缀
	 */
	//数据字典
	public static final String DICTIOINARY_CACHE = "DICTIOINARY_CACHE_";
	/**
	 * 默认存储路径
	 */
	public static final String DEFAULT_PHOTO_PATH = "/Users/panlei/photos";
	/**
	 * 图片存储参数配置名
	 */
	public static final String WXNEWS_PHOTO_PATH = "WXNEWS_PHOTO_PATH";
	
}
