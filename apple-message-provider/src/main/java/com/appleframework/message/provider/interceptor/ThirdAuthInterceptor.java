package com.appleframework.message.provider.interceptor;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.appleframework.message.provider.service.MessagePlusService;

@Component
@Aspect
public class ThirdAuthInterceptor {
		
	@Resource
	private MessagePlusService messagePlusService;
	
	@AfterReturning(value="execution(* com.appleframework.message.provider.service.impl.ThirdAuthServiceImpl.save(..))", 
			argNames="rtv", returning="rtv")
	public void afterSaveMethod(JoinPoint jp, final Object rtv) {
		Long id = (Long)rtv;
		messagePlusService.remove(id);
    }
	
	@AfterReturning(value="execution(* com.appleframework.message.provider.service.impl.ThirdAuthServiceImpl.delete(..))", 
			argNames="rtv", returning="rtv")
	public void afterDeleteMethod(JoinPoint jp, final Object rtv) {
		Long id = (Long)rtv;
		messagePlusService.remove(id);
    }
	
	@AfterReturning(value="execution(* com.appleframework.message.provider.service.impl.ThirdAuthServiceImpl.update(..))", 
			argNames="rtv", returning="rtv")
	public void afterUpdateMethod(JoinPoint jp, final Object rtv) {
		Long id = (Long)rtv;
		messagePlusService.remove(id);
    }	

}