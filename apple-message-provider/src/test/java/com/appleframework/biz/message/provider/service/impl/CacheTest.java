package com.appleframework.message.provider.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appleframework.cache.core.CacheManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class CacheTest {


	@Resource
	private CacheManager orderPushCacheManager;

	
	@Test
	public void testAddOpinion1() {
		try {
			orderPushCacheManager.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
