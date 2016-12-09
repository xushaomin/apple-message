package com.appleframework.message.service;

import com.appleframework.exception.AppleException;
import com.appleframework.model.page.Pagination;

import java.util.List;

import com.appleframework.message.entity.ThirdAuth;
import com.appleframework.message.model.ThirdAuthSo;

public interface ThirdAuthService {

	public ThirdAuth get(Long id);

	public Long save(ThirdAuth log) throws AppleException;
	
	public Long update(ThirdAuth log) throws AppleException;
	
	public Long update(Long id, ThirdAuth log) throws AppleException;

	public Long delete(Long id) throws AppleException;
	
	public List<ThirdAuth> findAll() throws AppleException;
		
	//public void freshen();
	
	public Pagination findByPage(Pagination page, ThirdAuthSo so);
	
	public List<ThirdAuth> findListByType(Integer type);
		
}

