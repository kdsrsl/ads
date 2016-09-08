/** 
 * Filename:    ProcessUtils.java 
 * Description:  
 * Copyright:   Copyright (c)2016 
 * Company:     VCYBER 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2016年3月5日 下午7:13:29 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年3月5日   senRsl      1.0         1.0 Version 
 */
package dc.android.common.utils;

import java.util.ArrayList;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

/**
 * @author senrsl
 *
 */
public class ProcessUtils {
	
	/**
	 * 服务是否运行
	 * @param ctx
	 * @param className
	 * @return
	 */
	public static boolean isWorked(Context ctx, String className) {
		ActivityManager myManager = (ActivityManager) ctx
				.getApplicationContext().getSystemService(
						Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager
				.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName().toString()
					.equals(className)) {
				return true;
			}
		}
		return false;
	}
}
