package com.appleframework.message.model;

import java.io.Serializable;

public class ThirdAuthSo implements Serializable {

	private static final long serialVersionUID = -258173529019557302L;
	
	private Integer type;
	private String name;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}