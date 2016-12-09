package com.appleframework.message.service;

import com.appleframework.exception.AppleException;
import com.appleframework.message.model.PushMessage;

public interface PushMessageService {

	public long push(PushMessage message) throws AppleException;	

}
