/** 
 * Filename:    RequestRunnable.java 
 * Description:  
 * Copyright:   Copyright (c)2016 
 * Company:     VCYBER 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2016年3月21日 下午2:13:37 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年3月21日   senRsl      1.0         1.0 Version 
 */  
package dc.android.common.web;

import java.util.Map;

import dc.android.common.SelfRetcode;
import dc.android.common.utils.NetUtils;
import dc.common.Logger;

/**
 *  网络交互
 * @author senrsl
 * 	高集成，外止
 *
 */
public class WebRequestRunnable implements Runnable {
	
	private String uri;
	private Map<String,String> map;
	private Callback cb;
	private int handleCode;

	
	
	public WebRequestRunnable(String uri, Map<String,String> map,Callback cb,int handleCode) {
		this.uri = uri;
		this.map = map;
		this.cb = cb;
		this.handleCode = handleCode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
			try {
				String result = null;
				if(NetUtils.isHttps(uri))
					result = WebFactory.getHttpsClientUtils().doPostAsJson(uri, map);
				else
					result = WebFactory.getHttpClientUtils().doPostAsJson(uri, map);
				cb.onResult(result, handleCode);
			} catch (Exception e) {
				cb.onErr(SelfRetcode.ERR);
				e.printStackTrace();
				Logger.w(e.getMessage());
			}
	}
	
	
	public interface Callback{
		public void  onResult(String result,int code);
		public void onErr(int code);
	}

}
