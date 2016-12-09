package com.appleframework.message.provider.plus.sms.bean;

import java.io.Serializable;

public class SmsCode implements Serializable {

	private static final long serialVersionUID = -8386378493075602507L;
	
	private Integer code;
	private String msg;
	
	private SmsResult result;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public SmsResult getResult() {
		return result;
	}

	public void setResult(SmsResult result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "SmsCode [code=" + code + ", msg=" + msg + ", result=" + result
				+ "]";
	}
	
}
