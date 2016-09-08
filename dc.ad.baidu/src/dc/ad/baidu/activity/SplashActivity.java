/** 
 * Filename:    SplashActivity.java 
 * Description:  
 * Copyright:   Copyright (c)2014 
 * Company:     bjwhds.com 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2014-3-26 下午8:17:03 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-3-26      senRsl      1.0         1.0 Version 
 */  
package dc.ad.baidu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.baidu.mobads.SplashAd;
import com.baidu.mobads.SplashAdListener;

import dc.ad.baidu.R;
import dc.ad.baidu.util.Log;

/**
 * 开屏广告
 *
 * @ClassName: SplashActivity
 * @author senRsl
 *
 * @Package: dc.ad.baidu.activity
 * @CreateTime: 2014-3-26 下午8:17:03
 *
 */
public class SplashActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		RelativeLayout adsParent=(RelativeLayout)this.findViewById(R.id.adsRl);
		@SuppressWarnings("unused")
		SplashAd splashAd=new SplashAd(this,adsParent,new SplashAdListener(){
			@Override
			public void onAdDismissed() {
				Log.i("开屏广告   onAdDismissed");
				jump();//跳转至您的应用主界面
			}
			@Override
			public void onAdFailed(String arg0) {
				Log.i("开屏广告   onAdDismissed");
			}
			@Override
			public void onAdPresent() {
				Log.i("开屏广告   onAdDismissed");
			}
		});
	}
	private void jump() {
		this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
		this.finish();
	}
	
	
}
