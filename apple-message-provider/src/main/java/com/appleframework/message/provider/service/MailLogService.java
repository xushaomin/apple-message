package com.appleframework.message.provider.service;

import com.appleframework.message.entity.MailLog;
import com.appleframework.exception.AppleException;

public interface MailLogService {

	public void insert(MailLog mailLog) throws AppleException;

	public void update(MailLog mailLog) throws AppleException;

	public MailLog get(Long id);
		
	public void delete(Long id) throws AppleException;
	
}
