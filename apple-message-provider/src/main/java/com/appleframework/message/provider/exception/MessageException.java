package com.appleframework.message.provider.exception;

public class MessageException extends Exception {

	private static final long serialVersionUID = -2000634350400480989L;

	private String code;

	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public MessageException() {
		
	}
	
	public MessageException(String code) {
		super();
		this.code = code;
	}
	
	public MessageException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public static MessageException create() {
		return new MessageException();
	}
	
	public static MessageException create(String code) {
		return new MessageException(code);
	}
	
	public static MessageException create(String code, String message) {
		return new MessageException(code, message);
	}

	@Override
	public String toString() {
		return "SmsException [code=" + code + ", message=" + message + "]";
	}
	
}
