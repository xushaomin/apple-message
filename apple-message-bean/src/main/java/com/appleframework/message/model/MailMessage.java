package com.appleframework.message.model;

import java.io.Serializable;
import java.util.Map;

public class MailMessage implements Serializable {

	private static final long serialVersionUID = 421881361210538964L;

	private String group;
	private String code;
	private String mailTo;
	private String mailSubject;
	private String mailBody;
	
	private String mailCc;
	private String mailBcc;
	private String isReadToMail;
		
	/**
	 * 数据集合
	 */
	private Map<String, String> data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailCc() {
		return mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	public String getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

	public String getIsReadToMail() {
		return isReadToMail;
	}

	public void setIsReadToMail(String isReadToMail) {
		this.isReadToMail = isReadToMail;
	}

	@Override
	public String toString() {
		return "MailMessage [group=" + group + ", code=" + code + ", mailTo=" + mailTo + ", mailSubject=" + mailSubject
				+ ", mailBody=" + mailBody + ", mailCc=" + mailCc + ", mailBcc=" + mailBcc + ", isReadToMail="
				+ isReadToMail + ", data=" + data + "]";
	}

}
