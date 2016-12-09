package com.appleframework.message.provider.utils.bean;

import java.io.Serializable;

public class PushResponse implements Serializable {

	private static final long serialVersionUID = -6873441760920447174L;

	private boolean resultOK = false;
	private String msgId;
	private String msgReturn;

	public boolean isResultOK() {
		return resultOK;
	}

	public void setResultOK(boolean resultOK) {
		this.resultOK = resultOK;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgReturn() {
		return msgReturn;
	}

	public void setMsgReturn(String msgReturn) {
		this.msgReturn = msgReturn;
	}

}
