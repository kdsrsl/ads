/** 
 * Filename:    InterstitialAdActivity.java 
 * Description:  
 * Copyright:   Copyright (c)2014 
 * Company:     bjwhds.com 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2014-3-26 下午8:06:54 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-3-26      senRsl      1.0         1.0 Version 
 */  
package dc.ad.baidu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.mobads.InterstitialAd;
import com.baidu.mobads.InterstitialAdListener;

import dc.ad.baidu.R;
import dc.ad.baidu.util.Log;

/**
 * 插屏广告
 *
 * @ClassName: InterstitialAdActivity
 * @author senRsl
 *
 * @Package: dc.ad.baidu.activity
 * @CreateTime: 2014-3-26 下午8:06:54
 *
 */
public class InterstitialAdActivity extends Activity {
	InterstitialAd interAd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_interstitial);
		interAd=new InterstitialAd(this);
		interAd.setListener(new InterstitialAdListener(){

			@Override
			public void onAdClick(InterstitialAd arg0) {
				Log.i("百度插屏广告 onAdClick");
			}

			@Override
			public void onAdDismissed() {
				Log.i("百度插屏广告 onAdDismissed");
				interAd.loadAd();
			}

			@Override
			public void onAdFailed(String arg0) {
				Log.i("百度插屏广告 onAdFailed");
			}

			@Override
			public void onAdPresent() {
				Log.i("百度插屏广告 onAdPresent");
			}

			@Override
			public void onAdReady() {
				Log.i("百度插屏广告 onAdReady");
			}
			
		});
		interAd.loadAd();
		
		Button btn=(Button)this.findViewById(R.id.btn_interstitial);
		btn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(interAd.isAdReady()){
					interAd.showAd(InterstitialAdActivity.this);
				}else{
					interAd.loadAd();
				}
			}
		});
	}
}
