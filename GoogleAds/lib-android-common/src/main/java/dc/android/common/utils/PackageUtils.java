/**  
 * Filename:    PackageUtils.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015-5-14 下午4:02:51  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015-5-14   senRsl      1.0            1.0 Version  
 */ 
package dc.android.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 *
 *
 *  PackageUtils
 * @author senrsl
 *
 * dc.vsf.util
 *  2015-5-14 下午4:02:51
 */
public class PackageUtils {
	
	public static PackageInfo getPackageInfo(Context ctx) throws NameNotFoundException{
		return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
	}
	public static PackageInfo getPackageInfo(Context ctx,String pkgName) throws NameNotFoundException{
		return ctx.getPackageManager().getPackageInfo(pkgName, 0);
	}
	
	/**
	 * <!-- 据包杀引 -->
    <uses-permission android:name="android.permission.INTERACT_ACROSS_USERS"/>
    <uses-permission android:name="android.permission.FORCE_STOP_PACKAGES"/>
	 * @param ctx
	 * @param pkgName
	 */
	public static void killApp(Context ctx,String pkgName){
//		ActivityManager actMgr = (ActivityManager)ctx.getSystemService(Context.ACTIVITY_SERVICE);
		//TODO 01
//		Logger.w( "程序是否运行:"+actMgr.isUserRunning(0));
//		actMgr.forceStopPackage(pkgName);
	}
	
	
	/**
	 * 获取当前应用文件所在路径
	 * @return
	 */
	public static String getAppFilePath(Context ctx) {
		return ctx.getFilesDir().getAbsolutePath();
	}
	
}
