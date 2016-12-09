package com.appleframework.message.service;

import com.appleframework.exception.AppleException;
import com.appleframework.model.page.Pagination;

import java.util.List;
import java.util.Map;

import com.appleframework.message.entity.MailTemplate;

public interface MailTemplateService {

	public void save(MailTemplate mailTemplate) throws AppleException;

	public void update(MailTemplate mailTemplate) throws AppleException;
	
	public void update(Long id, MailTemplate log) throws AppleException;
	
	public void delete(Long id) throws AppleException;

	public MailTemplate get(Long id);

	public MailTemplate getByGroupAndCode(String group, String code);
	
	public boolean isExistByThirdAuthId(Long thirdAuthId);
	
	public boolean isExistByGroupAndCode(String group, String code);
	
	public String buildContent(String group, String code, Map<String, String> data);
	
	public String buildContent(MailTemplate mailTemplate, Map<String, String> data);
	
	public Pagination findByPage(Pagination page, String keyword);

	public void deletes(List<Long> idList) throws AppleException;

}
