package com.appleframework.message.provider.service;

import com.appleframework.message.entity.SmsLog;
import com.appleframework.exception.AppleException;


public interface SmsLogService {

	public SmsLog get(Long id);

	/**
	 * 新添加
	 * 
	 * @param role
	 * @return
	 */
	public void save(SmsLog log) throws AppleException;
	
	/**
	 * 修改
	 * 
	 * @param role
	 * @return
	 */
	public void update(SmsLog log) throws AppleException;

	/**
	 * 删除短信记录
	 * 
	 * @param role
	 * @throws ServiceException
	 */
	public void delete(Long id) throws AppleException;

}

