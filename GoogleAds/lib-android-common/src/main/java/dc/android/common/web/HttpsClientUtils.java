package dc.android.common.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import dc.common.Global;

/**
     * Post请求连接Https服务
     * @param serverURL  请求地址
     * @param jsonStr    请求报文
     * @return
     * @throws Exception
     */

public class HttpsClientUtils extends BaseHttpClientUtils{
	  
	
     class SSLSocketFactoryImp extends SSLSocketFactory {
         SSLContext sslContext = SSLContext.getInstance("TLS");
 
        public SSLSocketFactoryImp(KeyStore truststore)
                throws NoSuchAlgorithmException, KeyManagementException,
                KeyStoreException, UnrecoverableKeyException {
            super(truststore);
 
            TrustManager tm = new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
 
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType)
                        throws java.security.cert.CertificateException {
                }
 
                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType)
                        throws java.security.cert.CertificateException {
                }
            };
            sslContext.init(null, new TrustManager[] { tm }, null);
        }
 
        @Override
        public Socket createSocket(Socket socket, String host, int port,
                boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host,
                    port, autoClose);
        }
 
        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }
   
	   
	  //忽略证书的验证
	     class SSLTrustManager implements javax.net.ssl.TrustManager,
		   javax.net.ssl.X509TrustManager ,HostnameVerifier{
		   public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		       return null;
		   }
		
		   public boolean isServerTrusted(
		           java.security.cert.X509Certificate[] certs) {
		       return true;
		   }
		
		   public boolean isClientTrusted(
		           java.security.cert.X509Certificate[] certs) {
		       return true;
		   }
		
		   public void checkServerTrusted(
		           java.security.cert.X509Certificate[] certs, String authType)
		           throws java.security.cert.CertificateException {
		       return;
		   }
		
		   public void checkClientTrusted(
		           java.security.cert.X509Certificate[] certs, String authType)
		           throws java.security.cert.CertificateException {
		       return;
		   }
		     
		       @Override
		   public boolean verify(String urlHostName, SSLSession session) { //允许所有主机
		       return true;
		   }
	   }
	   
	   
	   //封装 提供HttpsURLConnection 对象
	   private  HttpsURLConnection connect(String strUrl) throws Exception {
	             
	            javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
	            javax.net.ssl.TrustManager tm = new SSLTrustManager();
	            trustAllCerts[0] = tm;
	            javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
	                    .getInstance("SSL");
	            sc.init(null, trustAllCerts, null);
	            javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
	                    .getSocketFactory());
	              
	            HttpsURLConnection.setDefaultHostnameVerifier((HostnameVerifier) tm);
	              
	           URL url = new URL(strUrl);
	           HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
	           setHttpUrlConnectionParams(urlConn);
	          
	           return urlConn;
	       }


	@Override
	public HttpClient getHttpClient() throws Exception {
		
			 // 参数
			httpParams = new BasicHttpParams();
	        // 设置连接超时
	        HttpConnectionParams.setConnectionTimeout(httpParams, TIME_OUT);
	        // 设置socket超时
	        HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT);
			
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);
	         
	        SSLSocketFactory sf = new SSLSocketFactoryImp(trustStore);
	        //允许所有主机的验证
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	         
	        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
	        // 设置http和https支持
	        SchemeRegistry registry = new SchemeRegistry();
	        registry.register(new Scheme(Global.TRANS_HEAD_HTTP, PlainSocketFactory.getSocketFactory(), 80));
	        registry.register(new Scheme(Global.TRANS_HEAD_HTTPS, sf, 443));
	         
	        ClientConnectionManager ccm = new ThreadSafeClientConnManager(httpParams, registry);
	        httpClient = new DefaultHttpClient(ccm,httpParams);
//	        Logger.w("新建httpClient------https");
        return httpClient;
	}


	@Override
	public HttpURLConnection getHttpURLConnection(String url)throws Exception {
		return  connect(url);
	}
	
}