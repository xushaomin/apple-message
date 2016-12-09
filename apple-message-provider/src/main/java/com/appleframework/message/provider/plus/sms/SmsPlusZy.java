package com.appleframework.message.provider.plus.sms;

import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.appleframework.core.utils.StringUtility;
import com.appleframework.message.provider.exception.MessageException;
import com.appleframework.message.provider.plus.SmsMessagePlus;
import com.appleframework.boot.utils.HttpUtils;

public class SmsPlusZy implements SmsMessagePlus {

	private static final Log logger = LogFactory.getLog(SmsPlusZy.class);
		
	public String SMS_URL = "http://211.147.244.114:9801/CASServer/SmsAPI/SendMessage.jsp";
	
	private String uid;
	private String pwd;
	
	/*private String sendSMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }*/

	private String sendSmsMessage(String mobiles, String message) throws MessageException {
		String postData = getSmsMessageParams(mobiles, message);
		String resp = "";
		try {
			//resp = sendSMS(postData, SMS_URL);
			resp = HttpUtils.post(SMS_URL + postData, null);
			if(!StringUtility.isEmpty(resp) && resp.trim().indexOf("成功") > -1) {
				//<root return="0" info="成功" msgid="991821842" numbers="1" messages="1"/>
				int startIndex = resp.indexOf("msgid=");
				return resp.substring(startIndex + 7, startIndex + 16);
			}
			else {
				throw MessageException.create(resp);
			}
		} 
		catch (MessageException e) {
			logger.error(e.toString());
			throw e;
		} catch (Exception e) {
			logger.error(e);
			throw MessageException.create(null, e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	private String getSmsMessageParams(String mobiles, String message) {
		StringBuffer postData = new StringBuffer("?");
		postData.append("userid=" + uid);
		postData.append("&password=" + pwd);
		postData.append("&destnumbers=" + mobiles);
		postData.append("&sendtime=");
		postData.append("&msg=" + URLEncoder.encode(message));
		return postData.toString();
	}

	@Override
	public String doSend(String mobile, String content) throws MessageException {
		return sendSmsMessage(mobile, content);
	}

	@Override
	public void setThirdKey(String thirdKey) {
		this.uid = thirdKey;
	}

	@Override
	public void setThirdSecret(String thirdSecret) {
		this.pwd = thirdSecret;
	}

	@Override
	public void setThirdExtend(String thirdExtend) {
		
	}
	
}