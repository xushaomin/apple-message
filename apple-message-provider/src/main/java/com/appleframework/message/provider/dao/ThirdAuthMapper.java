package com.appleframework.message.provider.dao;

import org.springframework.stereotype.Repository;

import com.appleframework.message.entity.ThirdAuth;

@Repository
public interface ThirdAuthMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(ThirdAuth record);

    int insertSelective(ThirdAuth record);

    ThirdAuth selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThirdAuth record);

    int updateByPrimaryKey(ThirdAuth record);
}