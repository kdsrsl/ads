/** 
 * Filename:    BaseHttpClientUtils.java 
 * Description:  
 * Copyright:   Copyright (c)2016 
 * Company:     VCYBER 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2016年3月21日 下午5:19:40 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年3月21日   senRsl      1.0         1.0 Version 
 */  
package dc.android.common.web;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import dc.common.Global;
import dc.common.utils.FileUtils;
import dc.common.Logger;

/**
 * @author senrsl
 *
 */
public abstract class BaseHttpClientUtils {
	protected  HttpParams httpParams;
	protected  DefaultHttpClient httpClient;
	protected  String JSESSIONID; //定义一个静态的字段，保存sessionID

	protected  final int TIME_OUT = 10*1000;   //超时时间
	protected  final String ENCODE_DEFAULT = HTTP.UTF_8;   //默认编码
	
	protected  final String KEEP_ALIVE = "close";//keep-alive
	private final  String CONTENT_TYPE = "multipart/form-data";   //内容类型
	
	//非线程安全
	public  BaseHttpClientUtils() {
		try {
//			Logger.w("base http client create");
				getHttpClient();
		} catch (Exception e) {
//			Logger.w("init http client err:"+e.getMessage());
			e.printStackTrace();
			Logger.w(e.getMessage());
		}
	}


	public  abstract HttpClient getHttpClient() throws Exception;
	
	
    @SuppressWarnings("rawtypes")
    public  String doGet(String url, Map params) throws Exception {
        /* 建立HTTPGet对象 */
        String paramStr = "";
        if (params != null) {
            Iterator iter = params.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                paramStr += paramStr = "&" + key + "=" + val;
            }
        }
        if (!paramStr.equals("")) {
            paramStr = paramStr.replaceFirst("&", "?");
            url += paramStr;
        }
        HttpGet httpRequest = new HttpGet(url);
        String strResult = "doGetError";
        /* 发送请求并等待响应 */
        HttpResponse httpResponse = httpClient.execute(httpRequest);
        /* 若状态码为200 ok */
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            /* 读返回数据 */
            strResult = EntityUtils.toString(httpResponse.getEntity());
        } else {
            strResult = "Error Response: " + httpResponse.getStatusLine().toString();
        }
//        Log.v("TEST", strResult);
        Logger.w(strResult +httpResponse.getStatusLine().getStatusCode());
        return strResult;
    }
	
	public  String doPost(String url, List<NameValuePair> params) throws Exception{
	        /* 建立HTTPPost对象 */
	        HttpPost httpRequest = new HttpPost(url);
	        String strResult = "doPostError";
	        /* 添加请求参数到请求对象 */
	        if (params != null && params.size() > 0) {
	            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	        }
	        if(null != JSESSIONID){
	            httpRequest.setHeader("Cookie", "JSESSIONID="+JSESSIONID);
	        }
	        /* 发送请求并等待响应 */
	        HttpResponse httpResponse = httpClient.execute(httpRequest);
	        /* 若状态码为200 ok */
	        if (httpResponse.getStatusLine().getStatusCode() == 200) {
	            /* 读返回数据 */
	            strResult = EntityUtils.toString(httpResponse.getEntity());
	            /* 获取cookieStore */
	            CookieStore cookieStore = httpClient.getCookieStore();
	            List<Cookie> cookies = cookieStore.getCookies();
	            for(int i=0;i<cookies.size();i++){
	                if("JSESSIONID".equals(cookies.get(i).getName())){
	                    JSESSIONID = cookies.get(i).getValue();
	                    break;
	                }
	            }
	        }
//	        Log.v("TEST", strResult+"-------"+httpResponse.getStatusLine().getStatusCode());
//	        //日2
//	        Logger.w(strResult+"----doPost---"+httpResponse.getStatusLine().getStatusCode());
	        return strResult;
	}
	
	public  String doUpload(String requestUrl, String filename,String str) throws Exception {
		return doUpload(requestUrl, filename, str.getBytes(Global.ENCODE_UTF_8));
	}

	public  String doUpload(String requestUrl, String filename,File file) throws Exception {
		return doUpload(requestUrl, filename, FileUtils.file2byte(filename));
	}
	
	public  String doUpload(String requestUrl,String filename, byte[] b) throws Exception {
        String result = null;
        String PREFIX = "--" , LINE_END = "\r\n";
        String  BOUNDARY =  getBoundary();
            HttpURLConnection conn = getHttpURLConnection(requestUrl);
           setHttpUrlConnectionParams(conn, true,true,"POST",KEEP_ALIVE,getContentType(CONTENT_TYPE, BOUNDARY));

            if(b!=null)
            {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                DataOutputStream dos = new DataOutputStream( conn.getOutputStream());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * 这里重点注意：
                 * name里面的值为服务器端需要key   只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的   比如:abc.png
                 */
                sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""+filename+"\""+LINE_END);
//                sb.append("Content-Type: application/octet-stream; charset="+ENCODE_DEFAULT+LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                dos.write(b);
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                dos.close();
                /**
                 * 获取响应码  200=成功
                 * 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
//                Logger.w("响应码:"+res);
                if(res==200)
                {
                InputStream input =  conn.getInputStream();
                StringBuffer sb1= new StringBuffer();
                int ss ;
                while((ss=input.read())!=-1)
                {
                    sb1.append((char)ss);
                }
                result = sb1.toString();
//                Logger.w("返回:"+result);
                }
            }
        return result;
    }
	
	 public abstract HttpURLConnection getHttpURLConnection(String url) throws Exception;
	 
	 protected void setHttpUrlConnectionParams(HttpURLConnection conn,boolean doInput,boolean doOutput,String requestMethod,String keepAlive,String contentType) throws ProtocolException{
		 setHttpUrlConnectionParams(conn, TIME_OUT, doInput, doOutput, false, requestMethod, ENCODE_DEFAULT, keepAlive, contentType,null != JSESSIONID?null:String.format("JSESSIONID=%s", JSESSIONID));
	 }
	 protected void setHttpUrlConnectionParams(HttpURLConnection conn) throws ProtocolException{
		 setHttpUrlConnectionParams(conn, TIME_OUT, true, false, false, "GET", ENCODE_DEFAULT, KEEP_ALIVE,getContentType(CONTENT_TYPE, getBoundary()),null != JSESSIONID?null:String.format("JSESSIONID=%s", JSESSIONID));
	 }
	 protected void setHttpUrlConnectionParams(HttpURLConnection conn,int timeout,boolean doInput,boolean doOutput,boolean useCaches,String requestMethod,String charset,String keepAlive,String contentType,String cookie) throws ProtocolException{
		
		 conn.setReadTimeout(timeout);
         conn.setConnectTimeout(timeout);
         conn.setDoInput(doInput);  //允许输入流
         conn.setDoOutput(doOutput); //允许输出流
         conn.setUseCaches(useCaches);  //不允许使用缓存
         conn.setRequestMethod(requestMethod);  //请求方式
         conn.setRequestProperty("Charset", charset);  //设置编码
         conn.setRequestProperty("Connection", keepAlive);
         conn.setRequestProperty("Content-Type", contentType);
         if(null != cookie){
             conn.setRequestProperty("Cookie",cookie);
         }
	 }
	 
	 protected String getBoundary(){
		 return UUID.randomUUID().toString();  //边界标识   随机生成
	 }
	 
	 protected String getContentType(String contentType,String boundary){
		return String.format("%s;boundary=%s",contentType,boundary); 
	 }


	public  String doPostAsJson(String url, Map<String,String> params) throws JSONException, ClientProtocolException, IOException{
	        /* 建立HTTPPost对象 */
	        HttpPost httpRequest = new HttpPost(url);
	        String strResult = null;
	        /* 添加请求参数到请求对象 */
	        if (params != null && params.size() > 0) {
	        	httpRequest.setEntity(new StringEntity(getJsonObjectFromMap(params).toString(), ENCODE_DEFAULT));
//	            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
	        }
	        if(null != JSESSIONID){
	            httpRequest.setHeader("Cookie", "JSESSIONID="+JSESSIONID);
	        }
	        httpRequest.setHeader("Accept", "application/json");
		    httpRequest.setHeader("Content-type", "application/json");
	        
	        /* 发送请求并等待响应 */
	        HttpResponse httpResponse = httpClient.execute(httpRequest);
	        /* 若状态码为200 ok */
	        if (httpResponse.getStatusLine().getStatusCode() == 200) {
	            /* 读返回数据 */
	            strResult = EntityUtils.toString(httpResponse.getEntity(),ENCODE_DEFAULT);
	            /* 获取cookieStore */
	            CookieStore cookieStore = httpClient.getCookieStore();
	            List<Cookie> cookies = cookieStore.getCookies();
	            for(int i=0;i<cookies.size();i++){
	                if("JSESSIONID".equals(cookies.get(i).getName())){
	                    JSESSIONID = cookies.get(i).getValue();
	                    break;
	                }
	            }
	        }
//	        Log.e("TEST", strResult+"----");
//	        //日3
//	        Logger.w(strResult+"---doPostAsJson----"+httpResponse.getStatusLine().getStatusCode());
	        return strResult;
	    }
	
	public InputStream getInputStream(String url) throws IOException, Exception{
		return getHttpURLConnection(url).getInputStream();
	}

	public OutputStream getOutputStream(String url) throws IOException, Exception {
		return getHttpURLConnection(url).getOutputStream();
	}
	
	
	protected  JSONObject getJsonObjectFromMap(Map<String,String> params) throws JSONException {
		
		JSONObject jsonObject = new JSONObject();
		for (String str : params.keySet()) {
			jsonObject.put(str, params.get(str));
		}
	    return jsonObject;
	}
	
}
