/** 
 * Filename:    HttpClientUtils.java 
 * Description:  
 * Copyright:   Copyright (c)2015 
 * Company:     SENRSL 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2015-3-9 下午3:17:25 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2015-3-9   senRsl      1.0         1.0 Version 
 */
package dc.android.common.web;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

/**
 * 
 * 
 * @ClassName: HttpClientUtils
 * @author senrsl
 * 
 * @Package: dc.txt2audio.util
 * @CreateTime: 2015-3-9 下午3:17:25
 */
public class HttpClientUtils extends BaseHttpClientUtils{
	
	

	@Override
    public  HttpClient getHttpClient() throws Exception {
	        // 创建 HttpParams 以用来设置 HTTP 参数（这一部分不是必需的）
	        httpParams = new BasicHttpParams();
	        // 设置连接超时和 Socket 超时，以及 Socket 缓存大小
	        HttpConnectionParams.setConnectionTimeout(httpParams, TIME_OUT);
	        HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT);
	        HttpConnectionParams.setSocketBufferSize(httpParams, 8192);
	        // 设置重定向，缺省为 true
	        HttpClientParams.setRedirecting(httpParams, true);
	        // 设置 user agent
	        String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2) Gecko/20100115 Firefox/3.6";
	        HttpProtocolParams.setUserAgent(httpParams, userAgent);
	        // 创建一个 HttpClient 实例
	        // 注意 HttpClient httpClient = new HttpClient(); 是Commons HttpClient
	        // 中的用法，在 Android 1.5 中我们需要使用 Apache 的缺省实现 DefaultHttpClient
	        httpClient = new DefaultHttpClient(httpParams);
//	        Logger.w("新建httpClient------http");
        return httpClient;
    }
    
	


	@Override
	public HttpURLConnection getHttpURLConnection(String url) throws Exception {
		HttpURLConnection huc = (HttpURLConnection) new URL(url).openConnection();
		setHttpUrlConnectionParams(huc);
		return  huc;
	}
	
	

	
	
	
}