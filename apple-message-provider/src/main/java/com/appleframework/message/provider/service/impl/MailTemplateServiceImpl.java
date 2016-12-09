package com.appleframework.message.provider.service.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.appleframework.exception.AppleException;
import com.appleframework.exception.ServiceException;
import com.appleframework.model.page.Pagination;

import freemarker.template.Template;

import com.appleframework.message.entity.MailTemplate;
import com.appleframework.message.provider.dao.MailTemplateMapper;
import com.appleframework.message.provider.dao.extend.MailTemplateExtendMapper;
import com.appleframework.message.service.MailTemplateService;

@Service("mailTemplateService")
public class MailTemplateServiceImpl implements MailTemplateService {
	
	private static final Log logger = LogFactory.getLog(MailTemplateServiceImpl.class);
		
	@Resource
	private MailTemplateMapper mailTemplateMapper;
	
	@Resource
	private MailTemplateExtendMapper mailTemplateExtendMapper;
	
	@Override
	public void save(MailTemplate mailTemplate) throws AppleException {
		String code = mailTemplate.getTmpCode();
		String group = mailTemplate.getTmpGroup();
		if(this.isExistByGroupAndCode(group, code)) {
			throw new ServiceException("", "有相同的存在");
		}
		mailTemplate.setCreateTime(new Timestamp(System.currentTimeMillis()));
		mailTemplate.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		mailTemplate.setIsDelete(false);
		mailTemplateMapper.insert(mailTemplate);
	}

	@Override
	public void update(MailTemplate mailTemplate) throws AppleException {
		mailTemplate.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		mailTemplateMapper.updateByPrimaryKey(mailTemplate);
	}
	
	/**
	 * 修改
	 * 
	 * @param log
	 * @return
	 */
	public void update(Long id, MailTemplate temp) throws AppleException {
		String code = temp.getTmpCode();
		String group = temp.getTmpGroup();
		MailTemplate existTemp = this.getByGroupAndCode(group, code);
		if(null != existTemp && !id.equals(existTemp.getId())) {
			throw new ServiceException("", "有相同的存在");
		}
		MailTemplate po = this.get(id);
		String[] ignoreProperties = {"id", "createTime", "isDelete"};
		BeanUtils.copyProperties(temp, po, ignoreProperties);
		this.update(po);
	}

	@Override
	public MailTemplate get(Long id) {
		return mailTemplateMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 删除模板
	 * 
	 * @param role
	 * @throws AppleException
	 */
	public void delete(Long id) throws AppleException {
		MailTemplate temp = this.get(id);
		temp.setIsDelete(true);
		this.update(temp);
	}
		
	public MailTemplate getByGroupAndCode(String group, String code) {
		return mailTemplateExtendMapper.selectByGroupAndCode(group, code);
	}
	
	public boolean isExistByGroupAndCode(String group, String code) {
		MailTemplate temp = this.getByGroupAndCode(group, code);
		if(null == temp)
			return false;
		else
			return true;
	}

	@Override
	public boolean isExistByThirdAuthId(Long thirdAuthId) {
		return false;
	}
	
	public String buildContent(String group, String code, Map<String, String> data) {
		try {
			MailTemplate mailTemplate = this.getByGroupAndCode(group, code);
            Template template = new Template(null, new StringReader(mailTemplate.getTemplate()), null); 
			StringWriter out = new StringWriter();
			template.process(data, out);
			return out.getBuffer().toString();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public String buildContent(MailTemplate mailTemplate, Map<String, String> data) {
		try {
            Template template = new Template(null, new StringReader(mailTemplate.getTemplate()), null); 
			StringWriter out = new StringWriter();
			template.process(data, out);
			return out.getBuffer().toString();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	@Override
	public Pagination findByPage(Pagination page, String keyword) {
		List<MailTemplate> list = mailTemplateExtendMapper.selectByPage(page,keyword);
		page.setList(list);
		return page;
	}

	@Override
	public void deletes(List<Long> idList) throws AppleException {
		for (Long id : idList) {
			this.delete(id);
		}
	}

	
}