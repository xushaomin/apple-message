package com.appleframework.message.provider.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.appleframework.cache.core.CacheManager;
import com.appleframework.config.core.PropertyConfigurer;
import com.appleframework.exception.AppleException;
import com.appleframework.exception.ServiceException;
import com.appleframework.message.provider.model.SmsCaptcha;
import com.appleframework.message.provider.utils.Contants;
import com.appleframework.message.provider.utils.ResourceKeyGenerator;
import com.appleframework.message.service.SmsCaptchaService;
import com.appleframework.message.service.SmsSendService;

@Service("smsCaptchaService")
public class SmsCaptchaServiceImpl implements SmsCaptchaService {
	
	private static final Log logger = LogFactory.getLog(SmsCaptchaServiceImpl.class);
	
	public static String CAPTCHA_TIME_OUT = "CAPTCHA_TIME_OUT"; // 验证码过期
	public static String CAPTCHA_NO_EXIST = "CAPTCHA_NO_EXIST"; // 验证码无效
	public static String CAPTCHA_ERROR    = "CAPTCHA_ERROR";    // 验证码错误
	public static String CAPTCHA_INTERVAL = "CAPTCHA_INTERVAL"; // 验证码连续发送超过频率
		
	@Resource
	private CacheManager smsCacheManager;
	
	@Resource
	private SmsSendService smsSendService;
	
	/**
	 * 验证码验证
	 */
	public void validateCaptcha(String group, String code, String mobile, String captcha) throws AppleException {
		String key = this.getKey(group, code, mobile);
		SmsCaptcha smsCaptcha = this.smsCacheManager.get(key, SmsCaptcha.class);
		if (null == smsCaptcha) {
			logger.error("验证码不存在:" + mobile + "-" + captcha);
			throw new ServiceException(getClass(), CAPTCHA_NO_EXIST, mobile, captcha);
		}
		else {
			//判断是否过期
			if(smsCaptcha.isExpiration()) {
				logger.error("验证码过期:" + mobile + "-" + captcha);
				this.smsCacheManager.remove(key);
				throw new ServiceException(getClass(), CAPTCHA_TIME_OUT, mobile, captcha);
			}
			
			if (captcha.equalsIgnoreCase(smsCaptcha.getCaptcha())) {
				//this.smsCacheManager.remove(key);
				logger.info("验证码清除:" + mobile + "-" + captcha);
			}
			else {
				logger.error("验证码错误:" + mobile + "-" + captcha);
				throw new ServiceException(getClass(), CAPTCHA_ERROR, mobile, captcha);
			}
		}
	}
	  
	/**
	 * 发送验证码
	 */
	public String sendCaptcha(String group, String code, String mobile) throws AppleException {
		String key = this.getKey(group, code, mobile);
		SmsCaptcha smsCaptcha = this.smsCacheManager.get(key, SmsCaptcha.class);
		if (null != smsCaptcha) {
			Integer interval = PropertyConfigurer.getInteger(Contants.KEY_SMS_CAPTCHA_INTERVAL, 60); //发送时间间隔 单位秒
			if(smsCaptcha.isInterval(interval)) {
				logger.error("验证码发送频率限制:" + mobile);
				throw new ServiceException(getClass(), CAPTCHA_INTERVAL, mobile);
			}
		}
		
		String captcha = ResourceKeyGenerator.getRandomNum(6);;
		int captchaExpire = PropertyConfigurer.getInteger(Contants.KEY_SMS_CAPTCHA_EXPIRE, 60);
		smsCaptcha = SmsCaptcha.create(group, code, mobile, captcha, System.currentTimeMillis(), captchaExpire);
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("captcha", captcha);
				
		smsCacheManager.set(key, smsCaptcha);
		smsSendService.send(group, code, mobile, data);
		return captcha;
	}
	
	public String getKey(String group, String code, String mobile) {
		return group + "_" + code + "_" + mobile;
	}	
	
}
