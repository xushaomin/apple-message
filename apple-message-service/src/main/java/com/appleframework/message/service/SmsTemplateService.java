package com.appleframework.message.service;

import java.util.List;
import java.util.Map;

import com.appleframework.exception.AppleException;
import com.appleframework.model.page.Pagination;
import com.appleframework.message.entity.SmsTemplate;


public interface SmsTemplateService {

	public SmsTemplate get(Long id);

	/**
	 * 新添加
	 * 
	 * @param role
	 * @return
	 */
	public void save(SmsTemplate log) throws AppleException;
	
	/**
	 * 修改
	 * 
	 * @param role
	 * @return
	 */
	public void update(SmsTemplate log) throws AppleException;
	
	/**
	 * 修改
	 * 
	 * @param role
	 * @return
	 */
	public void update(Long id, SmsTemplate log) throws AppleException;

	/**
	 * 删除短信记录
	 * 
	 * @param role
	 * @throws ServiceException
	 */
	public void delete(Long id) throws AppleException;
		
	
	/**
	 * 获取模板
	 * 
	 * @param role
	 * @throws ServiceException
	 */
	public SmsTemplate getByGroupAndCode(String group, String code) throws AppleException;
		
	public String buildContent(String group, String code, Map<String, String> data);
	
	public String buildContent(SmsTemplate smsTemplate, Map<String, String> data);
	
	public boolean isExistByThirdAuthId(Long thirdAuthId);
	
	/**
	 * 获取所有短信模板
	 * 
	 * @throws AppleException
	 */
	//public List<SmsTemplate> findAll() throws AppleException;


	public Pagination findByPage(Pagination page, String keyword);

	public void deletes(List<Long> idList) throws AppleException;
	
}

