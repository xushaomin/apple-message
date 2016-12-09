package com.appleframework.message.provider.plus.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.alibaba.fastjson.JSON;
import com.appleframework.message.provider.exception.MessageException;
import com.appleframework.message.provider.plus.MailMessagePlus;
import com.appleframework.message.provider.plus.mail.bean.MailAuthenticator;
import com.appleframework.config.core.util.StringUtils;

public class EMailPlus implements MailMessagePlus {
	
	private String smtpHost;
	private Boolean smtpAuth;
	private String username;
	private String password;
	
	private String mailFrom;
	private String personalName;

	@Override
	public void setThirdKey(String thirdKey) {
		this.username = thirdKey;
	}

	@Override
	public void setThirdSecret(String thirdSecret) {
		this.password = thirdSecret;
	}

	@Override
	public void setThirdExtend(String thirdExtend) {
		MailAuthenticator mailAuthenticator = JSON.parseObject(thirdExtend, MailAuthenticator.class);
		this.smtpHost = mailAuthenticator.getSmtpHost();
		this.mailFrom = mailAuthenticator.getMailFrom();
		this.personalName = mailAuthenticator.getPersonalName();
		this.smtpAuth = mailAuthenticator.getSmtpAuth();
	}

	@Override
	public boolean doSend(String mailTo, String mailSubject, String mailBody, String ccMail, String bccMail, 
			String isReadToMail) throws MessageException {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.auth", smtpAuth.toString());
			Session mailSession = Session.getInstance(props);

			// 设置session,和邮件服务器进行通讯。
			mailSession.setDebug(false);
			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(mailSubject); // 设置邮件主题
			//message.setText(mailBody); // 设置邮件正文
			message.setContent(mailBody,"text/html; charset=utf-8");  
            
			//message.setHeader(mail_head_name, mail_head_value); // 设置邮件标题
			
			if(!StringUtils.isEmpty(isReadToMail)){
				message.setHeader("Disposition-Notification-To", isReadToMail);
			}
            
			message.setSentDate(new Date()); // 设置邮件发送日期
			Address address = new InternetAddress(mailFrom, personalName);
			message.setFrom(address); // 设置邮件发送者的地址
			
			// 创建邮件的接收者地址，并设置到邮件消息中
            // 将所有接收者地址都添加到邮件接收者属性中
            message.setRecipients(Message.RecipientType.TO, getAddress(mailTo));
            
            // 设置邮件cc地址
			if (!StringUtils.isEmpty(ccMail)){
				message.setRecipients(Message.RecipientType.CC, getAddress(ccMail));
			}
			// 设置邮件bcc地址
			if (!StringUtils.isEmpty(bccMail)){
				message.setRecipients(Message.RecipientType.BCC, getAddress(bccMail));
			}
			Transport transport = null;
			transport = mailSession.getTransport("smtp");
                                  
			message.saveChanges();
			transport.connect(smtpHost, username, password);
			if (message.getAllRecipients() == null) {
				throw MessageException.create("ERROR", "无收件人地址或收件人地址均无效");
			}
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
	        
			return true;
		} catch (Exception e) {
			throw MessageException.create("ERROR", e.getMessage());
		}
	}
	
	private Address[] getAddress(String mails) throws AddressException {
		String[] receivers = mails.split(";");
		// 去除无效地址
		List<String> mList = new ArrayList<String>();
		for (int i = 0; i < receivers.length; i++) {
			if (!StringUtils.isEmpty(receivers[i])) {
				Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
				Matcher matcher = pattern.matcher(receivers[i]);
				if (matcher.matches()) {
					mList.add(receivers[i]);
				}
			}
		}

		Address[] tos = null;
		if (mList != null && mList.size() > 0) {
			// 为每个邮件接收者创建一个地址
			tos = new InternetAddress[mList.size() ];
			for (int i = 0; i < mList.size(); i++) {
				tos[i] = new InternetAddress(mList.get(i));
			}
		}
		return tos;
	}

}
