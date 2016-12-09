package com.appleframework.message.provider.dao.extend;

import com.appleframework.model.page.Pagination;
import com.appleframework.message.entity.MailTemplate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MailTemplateExtendMapper {
	
	List<MailTemplate> selectByPage(@Param("page") Pagination page, @Param("keyword") String keyword);
	
	MailTemplate selectByGroupAndCode(@Param("group") String group, @Param("code") String code);

}