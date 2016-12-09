package com.appleframework.message.provider.plus.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.appleframework.message.provider.exception.MessageException;
import com.appleframework.message.provider.plus.SmsMessagePlus;

/**
 * 短信http接口的java代码调用示例 基于Apache HttpClient 4.3
 *
 * @author songchao
 * @since 2015-04-03
 */
public class SmsPlusGuodu implements SmsMessagePlus {

	private static final Log logger = LogFactory.getLog(SmsPlusGuodu.class);

	/* 具体参数解释请参见post方法头部 */
	/* 请填写您的用户名密码 username=用户名，password=密码 */
	private String username;
	private String password;
	private String appendID;

	@Override
	public String doSend(String mobile, String content) throws MessageException {		
		String resp = "";
		try {
			String sendTime = "";
			String mobiles[] = {mobile};
			resp = postSendMsg(username, password, content, mobiles, appendID, sendTime);
			if (resp.substring(0, 1).equals("03")) {
				return resp;
			} else {
				throw MessageException.create(null, resp);
			}
		} catch (MessageException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e);
			throw MessageException.create(null, e.getMessage());
		}
	}

	@Override
	public void setThirdKey(String thirdKey) {
		this.username = thirdKey;
	}

	@Override
	public void setThirdSecret(String thirdSecret) {
		this.password = thirdSecret;
	}

	@Override
	public void setThirdExtend(String thirdExtend) {
		
	}

	/** post方式 发送单条或批量消息 */
	/**
	 * @param OperID
	 *            用户名
	 * @param OperPass
	 *            密码
	 * @param Content
	 *            发送内容文字 长度最好不要超过500个字符。将会编两次GBK码
	 * @param DesMobiles[]
	 *            需要发送的手机号字符串数组 手机号个数请不要超过500个。
	 * @param AppendID
	 *            用户自扩展的号码 。
	 *            若扩展请填写号码，若不扩展请填写"",注意！通道号+国都用户身份号+AppendID总长不能超过20位。否则将发送失败。
	 *            具体号码定义，请参见国都资信通平台接口文档
	 * @param SendTime
	 *            发送时间 如果为定时消息。请填写，格式为yyyyMMddhhmmss 14位 若为实时消息，请填"";
	 * @return rec_string 国都返回的特殊格式的串
	 * @catch Exception
	 */
	public static String postSendMsg(String OperID, String OperPass, String Content, String DesMobiles[], String AppendID,
			String SendTime) {
		/* 将内容用URLEncoder编两次GBK */
		String EncoderContent = "";
		try {
			EncoderContent = URLEncoder.encode(Content, "GBK");
			EncoderContent = URLEncoder.encode(EncoderContent, "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* 将手机号从数组转变成一个用逗号分开的字符串，字符串末尾有逗号不影响消息下发。 */
		String DesMobile = "";
		for (int i = 0; i < DesMobiles.length; i++) {
			DesMobile = DesMobiles[i] + "," + DesMobile;
		}

		/* url地址 */
		String URL = "http://221.179.180.158:9008/HttpQuickProcess/submitMessageAll";

		/* 消息参数 */
		String str = "OperID=" + OperID + "&OperPass=" + OperPass + "&SendTime=" + SendTime + "&AppendID=" + AppendID
				+ "&DesMobile=" + DesMobile.trim() + "&Content=" + EncoderContent;

		if(logger.isInfoEnabled()) {
			logger.info("发送的内容为：" + str);
		}
		/* 使用post方式发送消息 */
		String response = postURL(str, URL);

		/* 返回响应 */
		return response;

	}

	/** post方式 发送个性化消息 */
	/**
	 * @param OperID
	 *            用户名
	 * @param OperPass
	 *            密码
	 * @param Content[]
	 *            发送内容文字 每个长度最好不要超过500个字符。请注意示例中编码方式
	 * @param DesMobiles[]
	 *            需要发送的手机号字符串数组 手机号个数请不要超过500个。
	 * @param AppendID
	 *            用户自扩展的号码 。
	 *            若扩展请填写号码，若不扩展请填写"",注意！通道号+国都用户身份号+AppendID总长不能超过20位。否则将发送失败。
	 *            具体号码定义，请参见国都资信通平台接口文档
	 * @param SendTime
	 *            发送时间 如果为定时消息。请填写，格式为yyyyMMddhhmmss 14位 若为实时消息，请填"";
	 * @return rec_string 国都返回的特殊格式的串
	 * @catch Exception
	 */
	public static String postSendSynamicMsg(String OperID, String OperPass, String Content[], String DesMobiles[],
			String AppendID, String SendTime) {

		/* 判断手机号个数和内容是否相等 */
		int contentLength = Content.length;
		int desmobileLength = DesMobiles.length;

		if (contentLength != desmobileLength) {
			return "手机号码个数和内容个数不相同";
		}

		/* 将内容从数组中拆分出来，每个内容用URLEncoder编一次GBK，之后整个串再进行一次URLEncoder编GBK */
		String EncoderContent = "";
		try {
			for (int i = 0; i < contentLength; i++) {
				String StringTemp = URLEncoder.encode(Content[i], "GBK");
				EncoderContent += StringTemp + ",";
			}
			EncoderContent = URLEncoder.encode(EncoderContent, "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* 将手机号从数组转变成一个用逗号分开的字符串，字符串末尾有逗号不影响消息下发。 */
		String DesMobile = "";
		for (int i = 0; i < DesMobiles.length; i++) {
			DesMobile = DesMobiles[i] + "," + DesMobile;
		}

		/* url地址 */
		String URL = "http://221.179.180.158:9008/HttpQuickProcess/submitMessageAll";

		/* 消息参数 */
		String str = "OperID=" + OperID + "&OperPass=" + OperPass + "&SendTime=" + SendTime + "&AppendID=" + AppendID
				+ "&DesMobile=" + DesMobile.trim() + "&Content=" + EncoderContent;

		System.out.println("发送的内容为：" + str);
		/* 使用post方式发送消息 */
		String response = postURL(str, URL);

		/* 返回响应 */
		return response;

	}

	/** post方式 发送url串 */
	/**
	 * @param commString
	 *            需要发送的url参数串
	 * @param address
	 *            需要发送的url地址
	 * @return rec_string 国都返回的自定义格式的串
	 * @catch Exception
	 */
	public static String postURL(String commString, String address) {
		String rec_string = "";
		URL url = null;
		HttpURLConnection urlConn = null;
		try {
			/* 得到url地址的URL类 */
			url = new URL(address);
			/* 获得打开需要发送的url连接 */
			urlConn = (HttpURLConnection) url.openConnection();
			/* 设置连接超时时间 */
			urlConn.setConnectTimeout(30000);
			/* 设置读取响应超时时间 */
			urlConn.setReadTimeout(30000);
			/* 设置post发送方式 */
			urlConn.setRequestMethod("POST");
			/* 发送commString */
			urlConn.setDoOutput(true);
			OutputStream out = urlConn.getOutputStream();
			out.write(commString.getBytes());
			out.flush();
			out.close();
			/* 发送完毕 获取返回流，解析流数据 */
			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GBK"));
			StringBuffer sb = new StringBuffer();
			int ch;
			while ((ch = rd.read()) > -1) {
				sb.append((char) ch);
			}
			rec_string = sb.toString().trim();
			/* 解析完毕关闭输入流 */
			rd.close();
		} catch (Exception e) {
			/* 异常处理 */
			rec_string = "-107";
			System.out.println(e);
		} finally {
			/* 关闭URL连接 */
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}
		/* 返回响应内容 */
		return rec_string;
	}
	
}