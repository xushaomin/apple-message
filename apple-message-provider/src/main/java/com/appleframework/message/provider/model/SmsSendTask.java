package com.appleframework.message.provider.model;

import java.io.Serializable;
import java.util.Map;

public class SmsSendTask implements Serializable {

	private static final long serialVersionUID = 7123117383783008509L;
	
	private boolean isDirect;

	private String group;
	
	private String code;
    
    private String mobile;
    
    private String content;

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

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isDirect() {
		return isDirect;
	}

	public void setDirect(boolean isDirect) {
		this.isDirect = isDirect;
	}

	@Override
	public String toString() {
		return "SmsSendTask [code=" + code + ", group=" + group + ", mobile=" + mobile + ", data=" + data + "]";
	}

}