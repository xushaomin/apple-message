package com.appleframework.message.provider.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appleframework.message.service.MailSendService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class MailSendServiceTest {
	
	@Resource
	private MailSendService mailSendService;

	@Test
	public void testSendSms() {
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("captcha", "1234");
			mailSendService.send("1", "1", "746167@qq.com", data);
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
