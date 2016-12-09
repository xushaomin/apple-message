package com.appleframework.message.model;

import java.io.Serializable;

public class SmsMessage implements Serializable {

	private static final long serialVersionUID = 421881361210538964L;

	private String group;
	private String code;
	private String mobiles;
	private String content;

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

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "PushSMS [group=" + group + ", code=" + code + ", mobiles="
				+ mobiles + ", content=" + content + "]";
	}

}
