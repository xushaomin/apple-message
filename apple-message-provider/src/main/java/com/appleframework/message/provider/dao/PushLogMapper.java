package com.appleframework.message.provider.dao;

import org.springframework.stereotype.Repository;

import com.appleframework.message.entity.PushLog;

@Repository
public interface PushLogMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(PushLog record);

    int insertSelective(PushLog record);

    PushLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PushLog record);

    int updateByPrimaryKey(PushLog record);
}