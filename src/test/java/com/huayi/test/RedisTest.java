package com.huayi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.huayi.common.cache.RedisCacheHelper;
import com.huayi.common.util.CacheUtil;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration ({"classpath:config/spring-context.xml"})
public class RedisTest {
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	@Test
	public void test() {
		redisTemplate.opsForValue().set("SHIRO_TEST", "PANLEI");
		System.out.println(redisTemplate.opsForValue().get("SHIRO_TEST"));
		CacheUtil.getRedisCacheHelper().set("SHIRO_TEST", "SHIRO_TEST");
		System.out.println(CacheUtil.getRedisCacheHelper().get("SHIRO_TEST"));
		CacheUtil.getCache().set("SHIRO_TEST", "CacheUtil");
		System.out.println(CacheUtil.getCache().get("SHIRO_TEST"));
	}
}
