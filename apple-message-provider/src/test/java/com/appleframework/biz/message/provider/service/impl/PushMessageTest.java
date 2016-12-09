package com.appleframework.message.provider.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.appleframework.message.model.PushMessage;
import com.appleframework.message.service.PushMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class PushMessageTest {


	@Resource
	private PushMessageService pushMessageService;

	
	@Test
	public void testAddOpinion1() {
		try {			
	
			for (int i = 0; i < 10; i++) {
				PushMessage push=new PushMessage();
				push.setContent("这是一个测试内容333");
				push.setTitle("张恒《华胥引》妆容引热议  眼神充满哀伤忧思");
				push.setMode(PushMessage.MESSAGE_MODE_DIRECTIONAL);
				//push.setReceiver("5994");
				push.setReceiver("943");
				push.setGroup("teacher");
				push.setCode("101");
				
				HashMap< String, String> data=new HashMap<String, String>();
				data.put("name", "shao");
				push.setData(data);
				pushMessageService.push(push);	
			}
			
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddOpinion6() {
		try {			
	
			PushMessage push=new PushMessage();
			push.setContent("这是一个测试内容  视频");
			push.setTitle("张恒《华胥引》妆容引热议  眼神充满哀伤忧思");
			push.setMode(PushMessage.MESSAGE_MODE_BROADCAST);
			push.setGroup("teacher");
			push.setCode("101");
			
			HashMap< String, String> data=new HashMap<String, String>();
			data.put("name", "3");
			push.setData(data);
			pushMessageService.push(push);	

			
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddOpinion2() {
		try {
			
 			PushMessage pushMessage = new PushMessage();
			pushMessage.setCode("jz-yaya");
			pushMessage.setContent("这是一个测试内容  视频");	
			pushMessage.setMode(PushMessage.MESSAGE_MODE_MULTICAST);
			pushMessage.setReceiver("188599b45fba4ff3a2ca494f2b4d6067");
			pushMessage.setTitle("张恒《华胥引》妆容引热议  眼神充满哀伤忧思");
			
			
			HashMap< String, String> data=new HashMap<String, String>();
			data.put("c_id", "100022");
			data.put("id", "480");
			data.put("tp", 1+"");//1娱乐新闻 2娱乐视频 3 普通新闻 
			pushMessage.setData(data);
			pushMessageService.push(pushMessage);				
			
			
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testAddOpinion3() {
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put("cid", "23");
 			PushMessage pushMessage = new PushMessage();
			pushMessage.setCode("1010");
			pushMessage.setContent("henhao-------");
			pushMessage.setData(data);
			pushMessage.setMode(PushMessage.MESSAGE_MODE_BROADCAST);
			pushMessage.setReceiver("");
			pushMessage.setTitle("测试");
			pushMessageService.push(pushMessage);
			
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
