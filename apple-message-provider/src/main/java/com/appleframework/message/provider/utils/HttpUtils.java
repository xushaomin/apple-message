package com.appleframework.message.provider.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

/**
 * http请求
 * @author moxq
 *
 */
@SuppressWarnings("deprecation")
public class HttpUtils {

	private static final Log logger = LogFactory.getLog(HttpUtils.class);

	/**
	 * post请求 ，超时默认20秒
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception 
	 */

	public static String post(String url, Map<String, String> params) throws Exception {
		return post(url, params, 20);
	}

	/**
	 * post请求
	 * @param url
	 * @param params
	 * @param timeout 超时时间，秒
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("resource")
	public static String post(String url, Map<String, String> params, int timeout) throws Exception {
		logger.debug("request url is " + url);
		long begin = System.currentTimeMillis();
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
		httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		String retVal = "";
		try {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			logger.debug(" post params is " + params);
			if (params != null) {
				for (Map.Entry<String, String> param : params.entrySet()) {
					 String value = param.getValue();
					 if(value !=null && !"".equals(value))
					    formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
				}
			}
			logger.debug("consume millis begin time is " + (System.currentTimeMillis() - begin));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
			HttpPost httppost = new HttpPost(url);
			//add acording to http://www.khotyn.com/2011/10/20/httpclient_4_0_1-extremely-slow/
			httppost.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
			httppost.setEntity(entity);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String temp = httpclient.execute(httppost, responseHandler);
			retVal = temp;
			//new String(temp.getBytes(HTTP.ISO_8859_1),
			// HTTP.UTF_8);
			long end = System.currentTimeMillis();
			logger.debug("consume millis end time is " + (end - begin));
			logger.debug("return result is " + retVal);
		} catch (IOException e) {
			logger.error("SocketTimeoutException request url is " + url);
			logger.error(" IOException is "+e.toString());
			throw e;
			
		} catch (Exception ex) {
			logger.error(" Exception is "+ex.toString());
			throw ex;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return retVal;
	}

	/**
	 * post请求
	 * @param url
	 * @param params
	 * @param timeout 超时时间，秒
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("resource")
	public static String post(String url, Map<String, String> params, int timeout, String charset) throws Exception {
		logger.debug("request url is " + url);
		long begin = System.currentTimeMillis();
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
		httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		String retVal = "";
		try {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			logger.debug(" post params is " + params);
			if (params != null) {
				for (Map.Entry<String, String> param : params.entrySet()) {
					 //String value = param.getValue();
					 //if(value !=null && !"".equals(value))
					     formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
				}
			}
			logger.debug("consume millis begin time is " + (System.currentTimeMillis() - begin));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, charset);
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(entity);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			retVal = new String(httpclient.execute(httppost, responseHandler).getBytes(HTTP.UTF_8), charset);
			long end = System.currentTimeMillis();
			logger.debug("consume millis end time is " + (end - begin));
			logger.debug("return result is " + retVal);
		} catch (IOException e) {
			logger.error("SocketTimeoutException request url is " + url);
			logger.error(" IOException is "+e.toString());
			throw e;
		} catch (Exception ex) {
			logger.error(" Exception is "+ex.toString());
			throw ex;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return retVal;
	}

	/**
	 * get请求
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static String get(String url, Map<String, String> params) throws IOException {
		logger.debug("request url is " + url);
		long begin = System.currentTimeMillis();
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setIntParameter("http.socket.timeout", 20000);
		String retVal = "";
		try {
			List<NameValuePair> qparams = new ArrayList<NameValuePair>();
			logger.debug(" get params is " + params);
			if (params != null) {
				for (Map.Entry<String, String> param : params.entrySet()) {
					 String value = param.getValue();
					 if(value !=null && !"".equals(value))
						qparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
				}
			}
			String paramstr = URLEncodedUtils.format(qparams, HTTP.UTF_8);
			if (StringUtils.isNotEmpty(paramstr)) {
				url = url + "&" + paramstr;
				logger.debug("get url is "+url);
			}
			logger.debug("consume millis begin time is " + (System.currentTimeMillis() - begin));
			HttpGet httpget = new HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			retVal = httpclient.execute(httpget, responseHandler);
			long end = System.currentTimeMillis();
			logger.debug("consume millis end time is " + (end - begin));
			logger.debug("return result is " + retVal);
		} catch (IOException e) {
			logger.error("SocketTimeoutException request url is " + url);
			logger.error(" IOException is "+e.toString());
			throw e;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return retVal;
	}

	
}