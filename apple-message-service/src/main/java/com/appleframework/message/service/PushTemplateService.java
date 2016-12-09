package com.appleframework.message.service;

import com.appleframework.exception.AppleException;
import com.appleframework.model.page.Pagination;

import java.util.List;
import java.util.Map;

import com.appleframework.message.entity.PushTemplate;

public interface PushTemplateService {

	public void save(PushTemplate pushTemplate) throws AppleException;

	public void update(PushTemplate pushTemplate) throws AppleException;
	
	public void update(Long id, PushTemplate log) throws AppleException;
	
	public void delete(Long id) throws AppleException;

	public PushTemplate get(Long id);

	public PushTemplate getByGroupAndCode(String group, String code);
	
	public boolean isExistByThirdAuthId(Long thirdAuthId);
	
	public boolean isExistByGroupAndCode(String group, String code);
	
	public String buildContent(String group, String code, Map<String, String> data);
	
	public String buildContent(PushTemplate pushTemplate, Map<String, String> data);
	
	public Pagination findByPage(Pagination page, String keyword);

	public void deletes(List<Long> idList) throws AppleException;

}
