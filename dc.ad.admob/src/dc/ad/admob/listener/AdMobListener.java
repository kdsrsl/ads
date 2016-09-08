/** 
 * Filename:    AdMobListener.java 
 * Description:  
 * Copyright:   Copyright (c)2014 
 * Company:     bjwhds.com 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2014-3-24 下午1:31:33 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-3-24      senRsl      1.0         1.0 Version 
 */  
package dc.ad.admob.listener;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.InterstitialAd;
import com.google.ads.AdRequest.ErrorCode;

import dc.ad.admob.util.Log;

/**
 *
 *
 * @ClassName: AdMobListener
 * @author senRsl
 *
 * @Package: dc.ad.admob.listener
 * @CreateTime: 2014-3-24 下午1:31:33
 *
 */
public class AdMobListener implements AdListener {
	
	private InterstitialAd interstitial;//插页广告专用
	
	
	public AdMobListener() {
		super();
	}

	public AdMobListener(InterstitialAd interstitial) {
		super();
		this.interstitial = interstitial;
	}

	/******************************监听 开始**********************************************/
	@Override
	public void onDismissScreen(Ad ad) {
		Log.i("当用户关闭通过onPresentScreen展示的全屏Activity且控制权将交还给应用时调用。");
	}

	@Override
	public void onFailedToReceiveAd(Ad ad, ErrorCode errorCode) {
		Log.i("当loadAd失败时发送，常见失败原因：网络故障、应用配置错误，或广告资源不足。您可以记下这些事件，以便进行调试："+errorCode);
	}

	@Override
	public void onLeaveApplication(Ad ad) {
		Log.i("当Ad触摸将启动新应用时调用。");
	}

	@Override
	public void onPresentScreen(Ad ad) {
		Log.i("当系统响应用户触摸广告的操作，在您的应用前创建了Activity并向用户展示全屏广告界面时调用。");
	}

	@Override
	public void onReceiveAd(Ad ad) {
		Log.i("当AdView.loadAd成功时发送。");
		 if (ad == interstitial) {
		      interstitial.show();
		    }
	}
	/******************************监听 结束**********************************************/
}
