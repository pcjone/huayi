package com.huayi.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huayi.common.Constants;
import com.huayi.common.cache.CacheManager;
import com.huayi.common.cache.RedisCacheHelper;
import com.huayi.common.cache.RedissonHelper;

@Configuration
public class CacheUtil {
	private static Logger logger = LogManager.getLogger(CacheUtil.class);
	private static CacheManager cacheManager;
	private static RedisCacheHelper redisCacheHelper;
	
	@Bean
	public CacheManager setCache() {
		cacheManager = getCache();
		return cacheManager;
	}

	public static CacheManager getCache() {
		if (cacheManager == null) {
			synchronized (CacheUtil.class) {
				if (cacheManager == null) {
					cacheManager = new RedissonHelper();
				}
			}
		}
		return cacheManager;
	}
	
	@Bean
	public RedisCacheHelper setRedisCacheHelper() {
		redisCacheHelper = getRedisCacheHelper();
		return redisCacheHelper;
	}
	
	public static RedisCacheHelper getRedisCacheHelper() {
		if (redisCacheHelper == null) {
			synchronized (CacheUtil.class) {
				if (redisCacheHelper == null) {
					redisCacheHelper = new RedisCacheHelper();
				}
			}
		}
		return redisCacheHelper;
	}
	
	/** 获取锁 */
	public static boolean getLock(String key) {
		try {
			if (!getRedisCacheHelper().exists(key)) {
				synchronized (CacheUtil.class) {
					if (!getRedisCacheHelper().exists(key)) {
						if (getRedisCacheHelper().setnx(key, String.valueOf(System.currentTimeMillis()))) {
							return true;
						}
					}
				}
			}
			int expires = 1000 * 60 * 3;
			String currentValue = (String) getRedisCacheHelper().get(key);
			if (currentValue != null && Long.parseLong(currentValue) < System.currentTimeMillis() - expires) {
				unlock(key);
				return getLock(key);
			}
			return false;
		} catch (Exception e) {
			logger.error(Constants.Exception_Head, e);
			return true;
		}
	}

	public static void unlock(String key) {
		getRedisCacheHelper().unlock(key);
	}
}
