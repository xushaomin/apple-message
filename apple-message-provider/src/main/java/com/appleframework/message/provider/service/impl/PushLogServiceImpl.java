package com.appleframework.message.provider.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.appleframework.message.entity.PushLog;
import com.appleframework.message.provider.dao.PushLogMapper;
import com.appleframework.message.provider.service.PushLogService;
import com.appleframework.exception.AppleException;

@Service("pushLogService")
public class PushLogServiceImpl implements PushLogService {	
	
	@Resource
	private PushLogMapper pushLogMapper;
	
	@Override
	public void insert(PushLog pushLog) throws AppleException {
		pushLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		pushLog.setIsDelete(false);
		pushLogMapper.insert(pushLog);
	}

	@Override
	public void update(PushLog pushLog) throws AppleException {
		pushLog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		pushLogMapper.updateByPrimaryKey(pushLog);
	}
	
	@Override
	public void delete(Long id) throws AppleException {
		PushLog pushLog = this.get(id);
		pushLog.setState((short)2);
		this.update(pushLog);
	}

	@Override
	public PushLog get(Long id) {
		return pushLogMapper.selectByPrimaryKey(id);
	}
	
}