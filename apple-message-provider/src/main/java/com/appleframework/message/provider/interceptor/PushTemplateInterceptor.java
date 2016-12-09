/*package com.appleframework.message.provider.interceptor;


import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.appleframework.message.service.SmsTemplateService;

@Component
@Aspect
public class PushTemplateInterceptor {
		
	@Resource
	private SmsTemplateService smsTemplateService;
	
	@AfterReturning(value="execution(* com.appleframework.message.provider.service.impl.SmsTemplateServiceImpl.save(..))", 
			argNames="rtv", returning="rtv")
	public void afterSaveMethod(JoinPoint jp, final Object rtv) {
		smsTemplateService.freshen();
    }
	
	@AfterReturning(value="execution(* com.appleframework.message.provider.service.impl.SmsTemplateServiceImpl.delete(..))", 
			argNames="rtv", returning="rtv")
	public void afterDeleteMethod(JoinPoint jp, final Object rtv) {
		smsTemplateService.freshen();
    }
	
	
	@AfterReturning(value="execution(* com.appleframework.message.provider.service.impl.SmsTemplateServiceImpl.update(..))", 
			argNames="rtv", returning="rtv")
	public void afterUpdateMethod(JoinPoint jp, final Object rtv) {
		smsTemplateService.freshen();
    }
	

}*/