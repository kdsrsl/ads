/**  
 * Filename:    NetUtils.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015年5月25日 下午6:03:11  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015年5月25日   senRsl      1.0            1.0 Version  
 */ 
package dc.android.common.utils;

import dc.common.Global;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络
 * @author senrsl
 *
 */
public class NetUtils {
	
//	private static boolean networkFlag = false;
	/**
	 *  是否存在可用连接
	 * @param context
	 * @return
	 */
	public static  boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
//    	networkFlag=false;
//        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo mobNetInfo = mConnectivityManager
//                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        NetworkInfo wifiNetInfo = mConnectivityManager
//                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
////        if(mobNetInfo==null){
////        	//Log.i("plj","mobNetInfo="+mobNetInfo);
////        	//Log.i("plj","wifiNetInfo="+wifiNetInfo);
////        	return false;
////        			
////        }
//        
//        if((null != mobNetInfo && mobNetInfo.isConnected() ) || (null != wifiNetInfo && wifiNetInfo.isConnected()))
//        	networkFlag = true;
//        else
//        	networkFlag = false;
//        	
//        
////        if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
////		     
////            networkFlag=false;
////        } else {
////        	networkFlag=true;
////       
////        }
//        return networkFlag;
	}
	
	
	public static boolean isHttps(String url){
		if(url.startsWith(Global.TRANS_HEAD_HTTPS))return true;
		return false;
	}
}
