package com.appleframework.message.provider.plus;

import com.appleframework.message.model.PushMessage;
import com.appleframework.message.provider.utils.bean.PushResponse;

public interface PushMessagePlus extends MessagePlus {
	
	public PushResponse doPush(PushMessage message);

}
