package com.appleframework.message.provider.plus.push;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.appleframework.config.core.PropertyConfigurer;
import com.appleframework.message.model.PushMessage;
import com.appleframework.message.provider.plus.PushMessagePlus;
import com.appleframework.message.provider.utils.bean.PushResponse;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushPlus implements PushMessagePlus {
	
	private final static Logger logger = LoggerFactory.getLogger(JPushPlus.class);
		
	private JPushClient jpushClient;
	
	private String appKey;
	private String appSecret;
        
    /***
	 * 实时推送APP 报警信息
	 * @param type
	 * @param time
     * @throws Exception 
	 */
	private PushResponse push(PushMessage message) {
		PushResponse andPushResponse = pushAndroid(message);
		PushResponse iosPushResponse = pushIos(message);
		if(andPushResponse.isResultOK())
			return andPushResponse;
		else
			return iosPushResponse;
	}
	
	/***
	 * 实时推送APP 报警信息
	 * @param type
	 * @param time
     * @throws Exception 
	 */
	public PushResponse pushAndroid(PushMessage message) {
		PushResult andResult = null;
		PushPayload andPushPayload = null;
		//Message msg = null;
		int mode = message.getMode();
		Map<String, String> data = message.getData();
		PushResponse pushResponse = new PushResponse();
		/*if(data.size() > 0) {
			msg = Message.newBuilder().setMsgContent(message.getContent()).addExtras(data).build();
		}
		else {
			msg = Message.newBuilder().setMsgContent(message.getContent()).build();
		}*/
		
		String pmsg = "";
		if(mode == PushMessage.MESSAGE_MODE_BROADCAST) {		
			andPushPayload = PushPayload.newBuilder()
					.setPlatform(Platform.android())
					.setAudience(Audience.all())
					.setNotification(Notification.newBuilder()
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                        		.setTitle(message.getTitle())
	                                .setAlert(message.getContent())
	                                //.setBadge(1)
	                                //.setSound("default")
	                                .addExtras(data)
	                                .build())
	                        .build())
					/*.setMessage(msg)*/.build();
		}
		else if(mode == PushMessage.MESSAGE_MODE_MULTICAST) {
			String receiver = message.getReceiver();
			String[] tags = receiver.split(",");
			andPushPayload = PushPayload.newBuilder()
					.setPlatform(Platform.android())
					.setAudience(Audience.tag(tags))
					.setNotification(Notification.newBuilder()
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                        		.setTitle(message.getTitle())
	                                .setAlert(message.getContent())
	                                //.setBadge(1)
	                                //.setSound("default")
	                                .addExtras(data)
	                                .build())
	                        .build())
					/*.setMessage(msg)*/.build();
		}
		else {
			String receiver = message.getReceiver();
			String[] alias = receiver.split(",");
			andPushPayload = PushPayload.newBuilder()
					.setPlatform(Platform.android())
					.setAudience(Audience.alias(alias))
					.setNotification(Notification.newBuilder()
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                        		.setTitle(message.getTitle())
	                                .setAlert(message.getContent())
	                                //.setBadge(1)
	                                //.setSound("default")
	                                .addExtras(data)
	                                .build())
	                        .build())
					/*.setMessage(msg)*/.build();
		}

		try {
			andResult = jpushClient.sendPush(andPushPayload);
			logger.info("ios push = " + andPushPayload.toString());
			//result = jpushIos.sendIosNotificationWithAlias(msgContent, extras, alias);
		} catch (APIRequestException e) {
			logger.error(e.getMessage());
			pmsg = e.getMessage();
			pushResponse.setMsgId(String.valueOf(e.getMsgId()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			pmsg = e.getMessage();
		}
		
		
		if(null != andResult) {
			pmsg = JSON.toJSONString(andResult);
			pushResponse.setMsgId(String.valueOf(andResult.msg_id));
			
		}
		pushResponse.setMsgReturn(pmsg);
		if(null != andResult && andResult.isResultOK()) {
			pushResponse.setResultOK(andResult.isResultOK());
		}
		return pushResponse;
	}
	
	/***
	 * 实时推送APP 报警信息
	 * @param type
	 * @param time
     * @throws Exception 
	 */
	public PushResponse pushIos(PushMessage message) {
		
		String apnsProduction = PropertyConfigurer.getValue("apns.production", "false");
		
		PushResult iosResult = null;
			
		PushPayload iosPushPayload = null;
		int type = message.getMode();
		Map<String, String> data = message.getData();
		/*if(data.size() > 0) {
			msg = Message.newBuilder().setMsgContent(message.getContent()).addExtras(data).build();
		}
		else {
			msg = Message.newBuilder().setMsgContent(message.getContent()).build();
		}*/
		PushResponse pushResponse = new PushResponse();
		String pmsg = "";
		if(type == PushMessage.MESSAGE_MODE_BROADCAST) {
			iosPushPayload = PushPayload.newBuilder()
					.setPlatform(Platform.ios())
					.setAudience(Audience.all())
					.setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(message.getContent())
	                                //.setBadge(1)
	                                //.setSound("default")
	                                .addExtras(data)
	                                .build())
	                        .build())
					.setOptions(Options.newBuilder().setApnsProduction(Boolean.valueOf(apnsProduction)).build())
					/*.setMessage(msg)*/.build();
		}
		else if(type == PushMessage.MESSAGE_MODE_MULTICAST) {
			String receiver = message.getReceiver();
			String[] tags = receiver.split(",");
			iosPushPayload = PushPayload.newBuilder()
					.setPlatform(Platform.ios())
					.setAudience(Audience.tag(tags))
					.setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(message.getContent())
	                                //.setBadge(1)
	                                //.setSound("default")
	                                .addExtras(data)
	                                .build())
	                        .build())
					.setOptions(Options.newBuilder().setApnsProduction(Boolean.valueOf(apnsProduction)).build())
					/*.setMessage(msg)*/.build();
		}
		else {
			String receiver = message.getReceiver();
			String[] alias = receiver.split(",");
			iosPushPayload = PushPayload.newBuilder()
					.setPlatform(Platform.ios())
					.setAudience(Audience.alias(alias))
					.setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(message.getContent())
	                                //.setBadge(1)
	                                //.setSound("default")
	                                .addExtras(data)
	                                .build())
	                        .build())
					.setOptions(Options.newBuilder().setApnsProduction(Boolean.valueOf(apnsProduction)).build())
					/*.setMessage(msg)*/.build();
		}

		try {
			iosResult = jpushClient.sendPush(iosPushPayload);
			logger.info("ios push = " + iosPushPayload.toString());
			//result = jpushIos.sendIosNotificationWithAlias(msgContent, extras, alias);
		} catch (APIRequestException e) {
			logger.error(e.getMessage());
			pmsg = e.getMessage();
			pushResponse.setMsgId(String.valueOf(e.getMsgId()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			pmsg = e.getMessage();
		}
		
		if(null != iosResult) {
			pmsg = JSON.toJSONString(iosResult);
			pushResponse.setMsgId(String.valueOf(iosResult.msg_id));
		}
		pushResponse.setMsgReturn(pmsg);
		if(null != iosResult && iosResult.isResultOK()) {
			pushResponse.setResultOK(iosResult.isResultOK());
		}
		return pushResponse;
	}

	@Override
	public PushResponse doPush(PushMessage message) {
		return this.push(message);
	}

	@Override
	public void setThirdKey(String thirdKey) {
		this.appKey = thirdKey;
	}

	@Override
	public void setThirdSecret(String thirdSecret) {
		this.appSecret = thirdSecret;
		
	}

	@Override
	public void setThirdExtend(String thirdExtend) {
		this.jpushClient = new JPushClient(appSecret, appKey);
	}	
    
}
