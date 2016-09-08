/**  
 * Filename:    VsfApplication.java  
 * Description:   
 * Copyright:   Copyright (c)2015  
 * Company:     vcyber  
 * @author:     senRsl senRsl@163.com  
 * @version:    1.0  
 * Create at:   2015年6月19日 下午5:53:24  
 *  
 * Modification History:  
 * Date             Author      Version     Description  
 * ------------------------------------------------------------------  
 * 2015年6月19日   senRsl      1.0            1.0 Version  
 */ 
package dc.android.ads;

import android.annotation.SuppressLint;
import android.app.Application;
import dc.common.Logger;

/**
 *
 * @author senrsl
 *
 */
public class VsfApplication extends Application {
	

	@Override
	public void onCreate() {
		super.onCreate();
//		Logger.start(this);//日0,7
//		Logger.startCat(this);
		Logger.w("VSF START");
		
		try {
		} catch (Exception e) {
			Logger.w(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	
	@Override
    public void onTerminate() {
        super.onTerminate();
//        Logger.w("程序终止");
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
//        Logger.w("低内存");
    }
    @SuppressLint("NewApi")
	@Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
//        Logger.w("内存清理");
    }
	
	
}
