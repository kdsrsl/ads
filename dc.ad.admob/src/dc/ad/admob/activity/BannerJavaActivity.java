/** 
 * Filename:    BannerJavaActivity.java 
 * Description:  
 * Copyright:   Copyright (c)2014 
 * Company:     bjwhds.com 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2014-3-24 上午10:49:19 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-3-24      senRsl      1.0         1.0 Version 
 */  
package dc.ad.admob.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import dc.ad.admob.R;
import dc.ad.admob.common.Global;
import dc.ad.admob.listener.AdMobListener;


/**
 * 横幅
 *	使用java方式创建
 * @ClassName: BannerJavaActivity
 * @author senRsl
 *
 * @Package: dc.ad.admob.activity
 * @CreateTime: 2014-3-24 上午10:49:19
 *
 */
public class BannerJavaActivity extends Activity {
	 private AdView adView;//2

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banner_java);
		
		// 创建adView
	    adView = new AdView(this, AdSize.BANNER, Global.AD_ID);
	    
	    AdRequest adRequest = new AdRequest();
	    adRequest.addTestDevice(AdRequest.TEST_EMULATOR);         // Emulator
	    adRequest.addTestDevice("TEST_DEVICE_ID");                // Test Android Device
	    adView.loadAd(adRequest);
//	    AdRequest request = new AdRequest();
//	    request.addTestDevice(AdRequest.TEST_EMULATOR);
//	    request.addTestDevice("E83D20734F72FB3108F104ABC0FFC738");    // My T-Mobile G1 test phone
//	    adView.loadAd(request);

	    // 查询LinearLayout，假设其已指定
	    // 属性android:id="@+id/mainLayout"
	    LinearLayout layout = (LinearLayout)findViewById(R.id.layout_banner_java);

	    // 在其中添加adView
	    layout.addView(adView);

	    // 启动一般性请求并在其中加载广告
	    adView.loadAd(new AdRequest());
	    
	    //设置监听
	    adView.setAdListener(new AdMobListener());

		
	}
	
	@Override
	  public void onDestroy() {
	    if (adView != null) {
	      adView.destroy();
	    }
	    super.onDestroy();
	  }

	

	 


}
