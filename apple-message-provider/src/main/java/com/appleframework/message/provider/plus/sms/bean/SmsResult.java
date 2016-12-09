package com.appleframework.message.provider.plus.sms.bean;

import java.io.Serializable;

public class SmsResult implements Serializable {

	private static final long serialVersionUID = 4848413026983330860L;
	
	private Integer count;
	private Integer fee;
	private Long sid;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "SmsResult [count=" + count + ", fee=" + fee + ", sid=" + sid
				+ "]";
	}

}
