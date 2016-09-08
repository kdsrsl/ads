/**  
 * Filename:    SharePreferencesUtils.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015-5-7 下午3:51:59  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015-5-7   senRsl      1.0            1.0 Version  
 */
package dc.android.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import dc.android.common.SelfContext;
import dc.common.Global;

/**
 *
 *
 * SharePreferencesUtils
 * 
 * @author senrsl
 *
 *         com.android.server.vsf.db 2015-5-7 下午3:51:59
 */
public class SharePreferencesUtils {

	private Context mContext;

	private static final String SP_NAME = SelfContext.DC_SERVICE_DATAMANAGER_SP;

	public SharePreferencesUtils(Context mContext) {
		super();
		this.mContext = mContext;
	}

	public String getSharedPreferencesValue(String key, String defValue) {
		SharedPreferences sp = mContext.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}

	public int getSharedPreferencesValue(String key, int defValue) {
		SharedPreferences sp = mContext.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
		return sp.getInt(key, defValue);
	}

	/**
	 * 
	 * @param key  值为Global.DEL时删除,否则存储
	 * @param value
	 * @return
	 */
	public boolean saveSharedPreferencesValue(String key, String value) {
		// Logger.w(
		// mContext.getBasePackageName()+"----"+mContext.getOpPackageName()+"----"+mContext.getPackageCodePath()+"----"+mContext.getPackageResourcePath());
		SharedPreferences preferences = mContext.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		if (Global.DEL.equals(value))
			editor.remove(key);
		else
			editor.putString(key, value);
		return editor.commit();
	}

	public boolean saveSharedPreferencesValue(String key, int value) {
		SharedPreferences preferences = mContext.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt(key, value);
		return editor.commit();
	}

}
