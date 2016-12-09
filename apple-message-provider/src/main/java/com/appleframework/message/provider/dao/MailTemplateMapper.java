package com.appleframework.message.provider.dao;

import org.springframework.stereotype.Repository;

import com.appleframework.message.entity.MailTemplate;

@Repository
public interface MailTemplateMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(MailTemplate record);

    int insertSelective(MailTemplate record);

    MailTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MailTemplate record);

    int updateByPrimaryKey(MailTemplate record);
}