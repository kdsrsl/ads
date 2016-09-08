/** 
 * Filename:    BannerCodeActivity.java 
 * Description:  
 * Copyright:   Copyright (c)2014 
 * Company:     bjwhds.com 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2014-3-26 下午7:28:34 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-3-26      senRsl      1.0         1.0 Version 
 */
package dc.ad.baidu.activity;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;

import dc.ad.baidu.util.Log;

/**
 * 横幅 代码方式
 * 
 * @ClassName: BannerCodeActivity
 * @author senRsl
 * 
 * @Package: dc.ad.baidu.activity
 * @CreateTime: 2014-3-26 下午7:28:34
 * 
 */
public class BannerCodeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.activity_banner_code);
//		LinearLayout rlMain = (LinearLayout)findViewById(R.layout.activity_banner_code);

		// 代码设置AppSid和Appsec，此函数必须在AdView实例化前调用
//		 AdView.setAppSid("debug");
//		 AdView.setAppSec("debug");

		// 人群属性
		AdSettings.setKey(new String[] { "baidu", "中 国 " });
		// AdSettings.setSex(AdSettings.Sex.FEMALE);
		// AdSettings.setBirthday(Calendar.getInstance());
		// AdSettings.setCity("上海");
		// AdSettings.setZip("123456");
		// AdSettings.setJob("工程师");
		// AdSettings.setEducation(AdSettings.Education.BACHELOR);
		// AdSettings.setSalary(AdSettings.Salary.F10kT15k);
		// AdSettings.setHob(new String[]{"羽毛球", "足球", "baseball"});
		// AdSettings.setUserAttr("k1","v1");
		// AdSettings.setUserAttr("k2","v2");

		RelativeLayout rlMain = new RelativeLayout(this);
		
		TextView tv = new TextView(this);
		tv.setText("我们地祖国是花园");
		rlMain.addView(tv);
		
		
		// 创建广告View
		AdView adView = new AdView(this);
		// 设置监听器
		adView.setListener(new AdViewListener() {
			public void onAdSwitch() {
				Log.i("度娘广告  onAdSwitch");
			}

			public void onAdShow(JSONObject info) {
				Log.i("度娘广告  onAdShow " + info.toString());
			}

			public void onAdReady(AdView adView) {
				Log.i("度娘广告  onAdReady " + adView);
			}

			public void onAdFailed(String reason) {
				Log.i("度娘广告  onAdFailed " + reason);
			}

			public void onAdClick(JSONObject info) {
				Log.i("度娘广告  onAdClick " + info.toString());
			}

			public void onVideoStart() {
				Log.i("度娘广告  onVideoStart");
			}

			public void onVideoFinish() {
				Log.i("度娘广告  onVideoFinish");
			}

			@Override
			public void onVideoClickAd() {
				Log.i("度娘广告  onVideoFinish");
			}

			@Override
			public void onVideoClickClose() {
				Log.i("度娘广告  onVideoFinish");
			}

			@Override
			public void onVideoClickReplay() {
				Log.i("度娘广告  onVideoFinish");
			}

			@Override
			public void onVideoError() {
				Log.i("度娘广告  onVideoFinish");
			}
		});
		rlMain.addView(adView);
		
		
		
		setContentView(rlMain);

	}

}
