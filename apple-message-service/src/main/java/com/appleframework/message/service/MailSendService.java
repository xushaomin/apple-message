package com.appleframework.message.service;

import java.util.Map;

import com.appleframework.exception.AppleException;

public interface MailSendService {

	//通过模板发送
	public void send(String group, String code, String mailTo, Map<String, String> data) throws AppleException;
	
	public void send(String group, String code, String mailTo, String ccMail, String bccMail, 
			String isReadToMail, Map<String, String> data) throws AppleException;
	
	public void send(String group, String code, String mailSubject, String mailBody, 
			String mailTo, String ccMail, String bccMail, String isReadToMail, 
			Map<String, String> data) throws AppleException;
	
}
