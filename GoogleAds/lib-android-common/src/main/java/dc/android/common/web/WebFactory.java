/** 
 * Filename:    WebFactory.java 
 * Description:  
 * Copyright:   Copyright (c)2016 
 * Company:     VCYBER 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2016年3月21日 下午6:34:46 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年3月21日   senRsl      1.0         1.0 Version 
 */  
package dc.android.common.web;



/**
 * @author senrsl
 *
 */
public class WebFactory {
	private static BaseHttpClientUtils baseHttpClientUtils;//只能一种
	private static BaseHttpClientUtils baseHttpsClientUtils;//只能二种
	
	public static BaseHttpClientUtils  getHttpClientUtils(){
		if(null == baseHttpClientUtils)baseHttpClientUtils = new HttpClientUtils();
		return baseHttpClientUtils;
	} 

	public static BaseHttpClientUtils  getHttpsClientUtils(){
		if(null == baseHttpsClientUtils)baseHttpsClientUtils = new HttpsClientUtils();
		return baseHttpsClientUtils;
	} 
	
}
