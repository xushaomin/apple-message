package com.appleframework.message.provider.service;

import com.appleframework.message.entity.PushLog;
import com.appleframework.exception.AppleException;

public interface PushLogService {

	public void insert(PushLog pushLog) throws AppleException;

	public void update(PushLog pushLog) throws AppleException;

	public PushLog get(Long id);
		
	public void delete(Long id) throws AppleException;
	
}
