/** 
 * Filename:    InterstitialActivity.java 
 * Description:  
 * Copyright:   Copyright (c)2014 
 * Company:     bjwhds.com 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2014-3-24 下午1:39:47 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-3-24      senRsl      1.0         1.0 Version 
 */  
package dc.ad.admob.activity;

import android.app.Activity;
import android.os.Bundle;

import com.google.ads.AdRequest;
import com.google.ads.InterstitialAd;

import dc.ad.admob.R;
import dc.ad.admob.common.Global;
import dc.ad.admob.listener.AdMobListener;

/**
 * 插页广告
 *
 * @ClassName: InterstitialActivity
 * @author senRsl
 *
 * @Package: dc.ad.admob.activity
 * @CreateTime: 2014-3-24 下午1:39:47
 *
 */
public class InterstitialActivity extends Activity {
	private InterstitialAd interstitial;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_interstitial);

	    // 制作插页式广告
	    interstitial = new InterstitialAd(this, Global.AD_ID);

	    // 创建广告请求
	    AdRequest adRequest = new AdRequest();

	    // 开始加载插页式广告
	    interstitial.loadAd(adRequest);

	    // 将广告监听器设为使用以下回调
	    interstitial.setAdListener(new AdMobListener(interstitial));
	  }
}
