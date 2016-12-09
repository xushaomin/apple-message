/**
 * 版权声明：深圳市广联赛讯有限公司 版权所有 违者必究 2012 
 * 日    期：12-8-3
 */
package com.appleframework.message.provider.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * <pre>
 *     Gop的上传文件编码格式为：
 *   fileType@BASE64编码的文件内容
 * </pre>
 *
 * @author 徐少敏
 * @version 1.0
 */
public class UploadFileUtils {

    public static final char FILE_SPERATOR = '@';
    public static final char NAME_SPERATOR = '.';

    /**
     * 获取文件的类型
     *
     * @param encodeFile
     * @return
     * @throws Exception 
     */
    public static final String getFileType(String encodeFile) throws RuntimeException {
    	String fileName = null;
    	fileName = getFileName(encodeFile);
    	if(null != fileName) {
    		int speratorIndex = fileName.indexOf(NAME_SPERATOR);
            if (speratorIndex > -1) {
                String fileType = fileName.substring(speratorIndex + 1);
                return fileType.toLowerCase();
            } else {
            	return fileName;
            }
    	}
    	else {
    		throw new RuntimeException("文件格式不对，正确格式为：<文件格式>@<文件内容>");
    	}
    }
    
    /**
     * 获取文件的名称
     *
     * @param encodeFile
     * @return
     * @throws Exception 
     */
    public static final String getFileName(String encodeFile) throws RuntimeException {
        int speratorIndex = encodeFile.indexOf(FILE_SPERATOR);
        if (speratorIndex > -1) {
            String fileType = encodeFile.substring(0, speratorIndex);
            return fileType.toLowerCase();
        } else {
            throw new RuntimeException("文件格式不对，正确格式为：<文件格式(包含格式)>@<文件内容>");
        }
    }

    /**
     * 获取文件的字节数组
     *
     * @param encodeFile
     * @return
     * @throws Exception
     */
    public static final byte[] decode(String encodeFile) throws RuntimeException {
        int speratorIndex = encodeFile.indexOf(FILE_SPERATOR);
        if (speratorIndex > -1) {
            String content = encodeFile.substring(speratorIndex + 1);
            return Base64.decodeBase64(content);
        } else {
            throw new RuntimeException("文件格式不对，正确格式为：<文件名称(包含格式)>@<文件内容>");
        }
    }

    /**
     * 将文件编码为BASE64的字符串
     *
     * @param bytes
     * @return
     */
    public static final String encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 将文件编码为一个字符串
     * @param uploadFile
     * @return
     */
    public static final String encode(UploadFile uploadFile){
        StringBuilder sb = new StringBuilder();
        sb.append(uploadFile.getFileName());
        sb.append(FILE_SPERATOR);
        sb.append(encode(uploadFile.getContent()));
        return sb.toString();
    }
    
    public static void main(String[] args) {
		String file = "jpg@sdfsfdsdfsdfsdfs";
		UploadFile upFile = new UploadFile(file);
		
		System.out.println(upFile.getFileName());
		System.out.println(upFile.getFileType());
	}
}

