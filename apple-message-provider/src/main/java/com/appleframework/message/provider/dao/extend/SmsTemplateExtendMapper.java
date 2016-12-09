package com.appleframework.message.provider.dao.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.appleframework.model.page.Pagination;
import com.appleframework.message.entity.SmsTemplate;

@Repository
public interface SmsTemplateExtendMapper {

	List<SmsTemplate> selectByPage(@Param("page") Pagination page, @Param("keyword") String keyword);
	
	SmsTemplate selectByGroupAndCode(@Param("group") String group, @Param("code") String code);

}