package com.appleframework.message.provider.service;

import java.util.List;

import com.appleframework.message.provider.plus.MessagePlus;

public interface MessagePlusService {

	public MessagePlus genrate(Long id);
	
	public List<MessagePlus> genrate(String ids);
	
	public void remove(Long id);

}

