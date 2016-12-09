package com.appleframework.message.provider.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.appleframework.message.entity.PushLog;
import com.appleframework.message.entity.PushTemplate;
import com.appleframework.message.model.PushMessage;
import com.appleframework.message.provider.plus.PushMessagePlus;
import com.appleframework.message.provider.service.MessagePlusService;
import com.appleframework.message.provider.service.PushLogService;
import com.appleframework.message.provider.utils.bean.PushResponse;
import com.appleframework.message.service.PushMessageService;
import com.appleframework.message.service.PushTemplateService;
import com.appleframework.core.utils.ObjectUtility;
import com.appleframework.exception.AppleException;
import com.appleframework.id.IdentityGenerator;
import com.appleframework.id.SnowflakeIdGenerator;

@Service("pushMessageService")
public class PushMessageServiceImpl implements PushMessageService {

	private static final Log logger = LogFactory.getLog(PushMessageServiceImpl.class);
		
	@Resource
	private PushLogService pushLogService;
	
	@Resource
	private MessagePlusService messagePlusService;
	
	@Resource
	private PushTemplateService pushTemplateService;
	
	private IdentityGenerator identityGenerator = SnowflakeIdGenerator.getInstance();
	
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	
	private void asyncPush(final PushMessage message) {
		 taskExecutor.execute(new Runnable(){
             public void run() {  
                 try {  
                	 doPush(message);
                 } catch (Exception e) {  
                     logger.error("Service handle exception",e);  
                 }
             }
         });
	}
	
	private void doPush(PushMessage message) throws Exception {
		if(ObjectUtility.isEmpty(message) || null == message.getCode()) {
			return;
		}
		String group = message.getGroup();
		String code = message.getCode();
		String content = message.getContent();
		
		
		PushTemplate template = pushTemplateService.getByGroupAndCode(group, code);
		if(null == content && null != template) {
			content = pushTemplateService.buildContent(template, message.getData());
			message.setContent(content);
		}
		
		Long thirdAuthId = template.getThirdAuthId();
		if(null  != thirdAuthId) {
			PushMessagePlus plus = (PushMessagePlus)messagePlusService.genrate(thirdAuthId);			
			// ios
			PushResponse result = plus.doPush(message);
			this.addLog(message, result);
		}
		

	}

	public long push(PushMessage message) {
		long msgId = identityGenerator.nextId("64");
		message.setId(msgId);
		this.asyncPush(message);
		return msgId;
	}
	
	private void addLog(PushMessage message, PushResponse result) {
		PushLog log = new PushLog();
		Map<String, String> data = message.getData();
		String dataJson = null;
		if(null != data) {
			dataJson = JSON.toJSONString(data);
		}
		log.setId(message.getId());
		log.setReceiver(message.getReceiver());
		log.setTmpGroup(message.getGroup());
		log.setTmpCode(message.getCode());
		log.setContent(message.getContent());
		log.setData(dataJson);
		log.setCountFail(0);
		log.setCountOk(result.isResultOK() == true ? 1: 0);
		log.setMsgReturn(result.getMsgReturn());
		log.setMsgId(result.getMsgId());
		log.setState((short)1);
		log.setTitle(message.getTitle());
		log.setMode(message.getMode());
		log.setIsDelete(false);
		
		try {
			pushLogService.insert(log);
		} catch (AppleException e) {
		}
	}

}