package com.appleframework.message.provider.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appleframework.message.service.SmsSendService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class SmsSendServiceTest {
	
	@Resource
	private SmsSendService smsSendService;

	@Test
	public void testSendSms() {
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("captcha", "1234");
			smsSendService.send("leyye", "1", "13760189357", data);
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
