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

import com.appleframework.message.entity.PushTemplate;
import com.appleframework.message.provider.dao.PushTemplateMapper;
import com.appleframework.message.provider.dao.extend.PushTemplateExtendMapper;
import com.appleframework.message.service.PushTemplateService;

@Service("pushTemplateService")
public class PushTemplateServiceImpl implements PushTemplateService {
	
	private static final Log logger = LogFactory.getLog(PushTemplateServiceImpl.class);

	@Resource
	private PushTemplateMapper pushTemplateMapper;
	
	@Resource
	private PushTemplateExtendMapper pushTemplateExtendMapper;
	
	@Override
	public void save(PushTemplate pushTemplate) throws AppleException {
		String code = pushTemplate.getTmpCode();
		String group = pushTemplate.getTmpGroup();
		if(this.isExistByGroupAndCode(group, code)) {
			throw new ServiceException("", "有相同的存在");
		}
		pushTemplate.setCreateTime(new Timestamp(System.currentTimeMillis()));
		pushTemplate.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		pushTemplate.setIsDelete(false);
		pushTemplateMapper.insert(pushTemplate);
	}

	@Override
	public void update(PushTemplate pushTemplate) throws AppleException {
		pushTemplate.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		pushTemplateMapper.updateByPrimaryKey(pushTemplate);
	}
	
	/**
	 * 修改
	 * 
	 * @param log
	 * @return
	 */
	public void update(Long id, PushTemplate temp) throws AppleException {
		String code = temp.getTmpCode();
		String group = temp.getTmpGroup();
		PushTemplate existTemp = this.getByGroupAndCode(group, code);
		if(null != existTemp && !id.equals(existTemp.getId())) {
			throw new ServiceException("", "有相同的存在");
		}
		PushTemplate po = this.get(id);
		String[] ignoreProperties = {"id", "createTime", "isDelete"};
		BeanUtils.copyProperties(temp, po, ignoreProperties);
		this.update(po);
	}

	@Override
	public PushTemplate get(Long id) {
		return pushTemplateMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 删除模板
	 * 
	 * @param role
	 * @throws AppleException
	 */
	public void delete(Long id) throws AppleException {
		PushTemplate temp = this.get(id);
		temp.setIsDelete(true);
		this.update(temp);
	}
		
	public PushTemplate getByGroupAndCode(String group, String code) {
		return pushTemplateExtendMapper.selectByGroupAndCode(group, code);
	}
	
	public boolean isExistByGroupAndCode(String group, String code) {
		PushTemplate temp = this.getByGroupAndCode(group, code);
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
			PushTemplate pushTemplate = this.getByGroupAndCode(group, code);
            Template template = new Template(null, new StringReader(pushTemplate.getTemplate()), null); 
			StringWriter out = new StringWriter();
			template.process(data, out);
			return out.getBuffer().toString();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	public String buildContent(PushTemplate pushTemplate, Map<String, String> data) {
		try {
            Template template = new Template(null, new StringReader(pushTemplate.getTemplate()), null); 
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
		List<PushTemplate> list = pushTemplateExtendMapper.selectByPage(page,keyword);
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