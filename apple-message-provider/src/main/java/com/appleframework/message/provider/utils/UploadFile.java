/**
 * 版权声明：深圳市广联赛讯有限公司 版权所有 违者必究 2012 
 * 日    期：12-8-1
 */
package com.appleframework.message.provider.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;

/**
 * <pre>
 * 上传的文件
 * </pre>
 *
 * @author 徐少敏
 * @version 1.0
 */
public class UploadFile {

	private String fileType;

	private String fileName;

	private byte[] content;

	/**
	 * 根据文件内容构造
	 *
	 * @param content
	 */
	public UploadFile(String fileName, String fileType, byte[] content) {
		this.content = content;
		this.fileName = fileName;
		this.fileType = fileType;
	}

	/**
	 * 根据文件构造
	 * 
	 * @param file
	 */
	public UploadFile(File file) {
		try {
			this.content = FileCopyUtils.copyToByteArray(file);
			this.fileName = file.getName();
			this.fileType = file.getName().substring(file.getName().lastIndexOf('.') + 1);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据文件构造
	 * 
	 * @param file
	 */
	public UploadFile(String encodeFile) {
		try {
			this.content = UploadFileUtils.decode(encodeFile);
			this.fileName = UploadFileUtils.getFileName(encodeFile);
			this.fileType = UploadFileUtils.getFileType(encodeFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String getFileType() {
		return fileType;
	}

	public byte[] getContent() {
		return content;
	}

	public String getFileName() {
		return fileName;
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}

}
