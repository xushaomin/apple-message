package com.appleframework.message.provider.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.appleframework.exception.AppleException;
import com.appleframework.exception.ServiceException;
import com.appleframework.model.page.Pagination;
import com.appleframework.message.provider.dao.ThirdAuthMapper;
import com.appleframework.message.provider.dao.extend.ThirdAuthExtendMapper;
import com.appleframework.message.entity.ThirdAuth;
import com.appleframework.message.model.ThirdAuthSo;
import com.appleframework.message.model.ThirdAuthType;
import com.appleframework.message.service.PushTemplateService;
import com.appleframework.message.service.SmsTemplateService;
import com.appleframework.message.service.ThirdAuthService;

/**
 * @author xusm
 * 
 */
@Service("thirdAuthService")
public class ThirdAuthServiceImpl implements ThirdAuthService {
	
	@Resource
	private ThirdAuthMapper thirdAuthMapper;
	
	@Resource
	private ThirdAuthExtendMapper thirdAuthExtendMapper;
	
	@Resource
	private SmsTemplateService smsTemplateService;
	
	@Resource
	private PushTemplateService pushTemplateService;
	
	public ThirdAuth get(Long id) {
		return thirdAuthMapper.selectByPrimaryKey(id);
	}

	/**
	 * 新添加
	 * 
	 * @param log
	 * @return
	 */
	public Long save(ThirdAuth log) throws AppleException {
		log.setCreateTime(new Date());
		log.setIsDelete(false);
		thirdAuthMapper.insert(log);
		return log.getId();
	}
	
	/**
	 * 修改
	 * 
	 * @param log
	 * @return
	 */
	public Long update(ThirdAuth log) throws AppleException {
		log.setUpdateTime(new Date());
		thirdAuthMapper.updateByPrimaryKey(log);
		return log.getId();
	}
	
	/**
	 * 修改
	 * 
	 * @param log
	 * @return
	 */
	public Long update(Long id, ThirdAuth log) throws AppleException {
		ThirdAuth po = this.get(id);
		String[] ignoreProperties = {"id", "createTime"};
		BeanUtils.copyProperties(log, po, ignoreProperties);
		this.update(po);
		return id;
	}

	/**
	 * 删除短信模板
	 * 
	 * @param role
	 * @throws AppleException
	 */
	public Long delete(Long id) throws AppleException {
		ThirdAuth auth = this.get(id);
		if(auth.getType() == ThirdAuthType.TYPE_SMS) {
			if(smsTemplateService.isExistByThirdAuthId(id)) {
				throw new ServiceException("", "有短信模板在使用“" + auth.getName() + "”的第三方帐号信息发送短信！");
			}
		}
		if(auth.getType() == ThirdAuthType.TYPE_PUSH) {
			if(pushTemplateService.isExistByThirdAuthId(id)) {
				throw new ServiceException("", "有短信模板在使用“" + auth.getName() + "”的第三方帐号信息发送短信！");
			}
		}
		auth.setIsDelete(true);
		return update(auth);
	}
	
	/**
	 * 获取所有短信模板
	 * 
	 * @throws AppleException
	 */
	public List<ThirdAuth> findAll() throws AppleException {
		return thirdAuthExtendMapper.selectByAll();
	}
	
	@Override
	public Pagination findByPage(Pagination page, ThirdAuthSo so) {
		List<ThirdAuth> list = thirdAuthExtendMapper.selectByPage(page, so);
		page.setList(list);
		return page;
	}
	
	@Override
	public List<ThirdAuth> findListByType(Integer type) {
		return thirdAuthExtendMapper.selectByType(type);
	}	
	
}
