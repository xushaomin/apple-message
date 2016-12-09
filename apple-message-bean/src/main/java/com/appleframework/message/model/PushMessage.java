package com.appleframework.message.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PushMessage implements Serializable {

	private static final long serialVersionUID = -5980947907153952876L;
	
	public static int MESSAGE_MODE_BROADCAST   = 1;
	public static int MESSAGE_MODE_MULTICAST   = 2;
	public static int MESSAGE_MODE_DIRECTIONAL = 3;
	
	/**
	 * 消息ID
	 */
	private Long id;
	
	/**
	 * 消息分组
	 */
	private String group;
	
	/**
	 * 消息编码
	 */
	private String code;

	/**
	 * 发送类型， =1广播 =2组播  =3定向
	 */
	private int mode;
	
	/**
	 * 消息标题
	 */
	private String title;
	
	/**
	 * 消息类容，于code 组合为任何类型消息，content 根据 format 可表示为 text,json ,xml数据格式
	 */
	private String content;

	/**
	 * 消息发送者接收者 type=3 receiver为用户的ID号 ，type=2为组播的tag，type=1为空
	 */
	private String receiver;

	/**
	 * 数据集合
	 */
	private Map<String, String> data;

	private long timestamp;

	public PushMessage() {
		data = new HashMap<String, String>();
		timestamp = System.currentTimeMillis();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setData(HashMap<String, String> data) {
		this.data = data;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	
}
