package com.appleframework.message.service;

import com.appleframework.exception.AppleException;

public interface SmsCaptchaService {

	/**
	 * 验证码验证
	 */
	public void validateCaptcha(String group, String code, String mobile, String captcha) throws AppleException;

	/**
	 * 发送验证码
	 */
	public String sendCaptcha(String group, String code, String mobile) throws AppleException;

}
