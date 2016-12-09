package com.appleframework.message.provider.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appleframework.message.service.SmsCaptchaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class SmsCaptchServiceTest {
	
	@Resource
	private SmsCaptchaService smsCaptchaService;

	@Test
	public void testSendCaptcha() {
		try {
			smsCaptchaService.sendCaptcha("parent", "12", "13760189357");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testValidateCaptcha() {
		try {
			smsCaptchaService.validateCaptcha("jz-yaya", "8", "13760189357", "123456");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddOpinion3() {
		try {
			smsCaptchaService.validateCaptcha("jz-yaya", "8", "13049890168", "870205");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
