package com.appleframework.message.provider.plus.mail.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class MailAuthenticator implements Serializable {

	private static final long serialVersionUID = 6104699292671666450L;
	
	private String smtpHost;
	private Boolean smtpAuth;
	private String mailFrom;
	private String personalName;

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public Boolean getSmtpAuth() {
		return smtpAuth;
	}

	public void setSmtpAuth(Boolean smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}
	
	public static void main(String[] args) {
		MailAuthenticator mailAuthenticator = new MailAuthenticator();
		mailAuthenticator.setMailFrom("xushaomin@9zhitx.com");
		mailAuthenticator.setPersonalName("徐少敏");
		mailAuthenticator.setSmtpAuth(false);
		mailAuthenticator.setSmtpHost("smtp.9zhitx.com");
		System.out.println(JSON.toJSON(mailAuthenticator));
	}

}
