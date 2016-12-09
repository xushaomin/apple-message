package com.appleframework.message.provider.model;

import java.io.Serializable;

public class SmsCaptcha implements Serializable {

	private static final long serialVersionUID = -6216680869123616823L;

	private String group;
	private String code;
	private String mobile;
	private String captcha;
	private Long time;
	private Integer expiration = 60; //过期时间 单位秒

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Integer getExpiration() {
		return expiration;
	}

	public void setExpiration(Integer expiration) {
		this.expiration = expiration;
	}

	public SmsCaptcha() {
	}
	
	public boolean isExpiration() {
		long now = System.currentTimeMillis();
		if(now - time > expiration * 1000) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isInterval(Integer interval) {
		long now = System.currentTimeMillis();
		if(now - time <= interval * 1000) {
			return true;
		}
		else {
			return false;
		}
	}
	

	public SmsCaptcha(String group, String code, String mobile, String captcha, long time) {
		super();
		this.code = code;
		this.group = group;
		this.mobile = mobile;
		this.captcha = captcha;
		this.time = time;
	}

	public static SmsCaptcha create(String group, String code, String mobile,
			String captcha, long time) {
		return new SmsCaptcha(group, code, mobile, captcha, time);
	}
	
	public SmsCaptcha(String group, String code, String mobile, String captcha,
			long time, Integer expiration) {
		super();
		this.code = code;
		this.group = group;
		this.mobile = mobile;
		this.captcha = captcha;
		this.time = time;
		this.expiration = expiration;
	}
	
	public static SmsCaptcha create(String group, String code, String mobile,
			String captcha, long time, Integer expiration) {
		return new SmsCaptcha(group, code, mobile, captcha, time, expiration);
	}

	@Override
	public String toString() {
		return "SmsCaptcha [code=" + code + ", group=" + group + ", mobile="
				+ mobile + ", captcha=" + captcha + ", time=" + time
				+ ", expiration=" + expiration + "]";
	}

}
