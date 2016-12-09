package com.appleframework.message.provider.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.appleframework.message.entity.MailLog;
import com.appleframework.message.entity.MailTemplate;
import com.appleframework.message.model.MailMessage;
import com.appleframework.message.provider.exception.MessageException;
import com.appleframework.message.provider.plus.MailMessagePlus;
import com.appleframework.message.provider.service.MailLogService;
import com.appleframework.message.provider.service.MessagePlusService;
import com.appleframework.message.service.MailSendService;
import com.appleframework.message.service.MailTemplateService;
import com.appleframework.core.utils.ObjectUtility;
import com.appleframework.exception.AppleException;
import com.appleframework.id.IdentityGenerator;
import com.appleframework.id.SnowflakeIdGenerator;

@Service("mailSendService")
public class MailSendServiceImpl implements MailSendService {

	private static final Log logger = LogFactory.getLog(MailSendServiceImpl.class);
		
	@Resource
	private MailLogService mailLogService;
	
	@Resource
	private MessagePlusService messagePlusService;
	
	@Resource
	private MailTemplateService mailTemplateService;
	
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	
	private IdentityGenerator identityGenerator = SnowflakeIdGenerator.getInstance();
	
	private void asyncSend(final MailMessage message) {
		 taskExecutor.execute(new Runnable(){
            public void run() {  
                try {  
                   send(message);
                } catch (Exception e) {  
                    logger.error("Service handle exception",e);  
                }
            }
        });
	}
	
	private void send(MailMessage message) throws Exception {
		if(ObjectUtility.isEmpty(message) || null == message.getCode()) {
			return;
		}
		String group = message.getGroup();
		String code = message.getCode();
		String mailBody = message.getMailBody();
		String mailSubject = message.getMailSubject();
		
		MailTemplate template = mailTemplateService.getByGroupAndCode(group, code);
		if(null == mailBody && null != template) {
			mailBody = mailTemplateService.buildContent(template, message.getData());
			message.setMailBody(mailBody);
		}
		
		if(null == mailSubject && null != template) {
			mailSubject = template.getTitle();
			message.setMailSubject(mailSubject);
		}
		
		Long thirdAuthId = template.getThirdAuthId();
		if(null  != thirdAuthId) {
			MailMessagePlus plus = (MailMessagePlus)messagePlusService.genrate(thirdAuthId);
			try {
				boolean result = plus.doSend(message.getMailTo(), message.getMailSubject(), message.getMailBody(),
						message.getMailCc(), message.getMailBcc(), message.getIsReadToMail());
				this.addLog(message, result, "OK");
			} catch (MessageException e) {
				this.addLog(message, false, e.getMessage());
			}			
		}
	}

	
	@Override
	public void send(String group, String code, String mailTo, String ccMail, String bccMail, 
			String isReadToMail, Map<String, String> data) throws AppleException {
		MailMessage message = new MailMessage();
		message.setGroup(group);
		message.setCode(code);
		message.setMailTo(mailTo);
		message.setMailCc(ccMail);
		message.setMailBcc(bccMail);
		message.setIsReadToMail(isReadToMail);
		message.setData(data);
		asyncSend(message);
	}
	
	@Override
	public void send(String group, String code, String mailSubject, String mailBody, 
			String mailTo, String ccMail, String bccMail, String isReadToMail, 
			Map<String, String> data) throws AppleException {
		MailMessage message = new MailMessage();
		message.setGroup(group);
		message.setCode(code);
		message.setMailTo(mailTo);
		message.setMailCc(ccMail);
		message.setMailBcc(bccMail);
		message.setIsReadToMail(isReadToMail);
		message.setData(data);
		asyncSend(message);
	}
	
	@Override
	public void send(String group, String code, String mailTo, 
			Map<String, String> data) throws AppleException {
		MailMessage message = new MailMessage();
		message.setGroup(group);
		message.setCode(code);
		message.setMailTo(mailTo);
		message.setData(data);
		asyncSend(message);
	}
	
	private void addLog(MailMessage message, boolean result, String errorMsg) {
		long msgId = identityGenerator.nextId("64");
		MailLog log = new MailLog();
		Map<String, String> data = message.getData();
		String dataJson = null;
		if(null != data) {
			dataJson = JSON.toJSONString(data);
		}
		log.setId(msgId);
		log.setMail(message.getMailTo());
		log.setTmpGroup(message.getGroup());
		log.setTmpCode(message.getCode());
		log.setContent(message.getMailBody());
		log.setData(dataJson);
		log.setCountFail(0);
		log.setCountOk(result == true ? 1: 0);
		log.setMailReturn(errorMsg);
		log.setState((short)1);
		log.setTitle(message.getMailSubject());
		log.setIsDelete(false);
		
		try {
			mailLogService.insert(log);
		} catch (AppleException e) {
		}
	}

}