package com.appleframework.message.provider.plus;

import com.appleframework.message.provider.exception.MessageException;

public interface SmsMessagePlus extends MessagePlus{
	
	public String doSend(String mobile, String content) throws MessageException;
	
}
