package com.appleframework.message.provider.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.appleframework.message.entity.MailLog;
import com.appleframework.message.provider.dao.MailLogMapper;
import com.appleframework.message.provider.service.MailLogService;
import com.appleframework.exception.AppleException;

@Service("MailLogService")
public class MailLogServiceImpl implements MailLogService {
	
	@Resource
	private MailLogMapper mailLogMapper;

	@Override
	public void insert(MailLog mailLog) throws AppleException {
		mailLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		mailLog.setIsDelete(false);
		mailLogMapper.insert(mailLog);
	}

	@Override
	public void update(MailLog mailLog) throws AppleException {
		mailLog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		mailLogMapper.updateByPrimaryKey(mailLog);
	}
	
	@Override
	public void delete(Long id) throws AppleException {
		MailLog mailLog = this.get(id);
		mailLog.setState((short)2);
		this.update(mailLog);
	}

	@Override
	public MailLog get(Long id) {
		return mailLogMapper.selectByPrimaryKey(id);
	}

}