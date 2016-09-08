/** 
 * Filename:    MainActivity.java 
 * Description:  
 * Copyright:   Copyright (c)2014 
 * Company:     bjwhds.com 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2014-3-24 上午11:11:41 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-3-24      senRsl      1.0         1.0 Version 
 */  
package dc.ad.admob.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import dc.ad.admob.R;

/**
 *
 *
 * @ClassName: MainActivity
 * @author senRsl
 *
 * @Package: dc.ad.admob.activity
 * @CreateTime: 2014-3-24 上午11:11:41
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void show(View v){
		Intent intent = null;
		switch(v.getId()){
		case R.id.btn_banner_java:
			intent = new Intent(this,BannerJavaActivity.class);
			break;
		case R.id.btn_interstitial:
			intent = new Intent(this,InterstitialActivity.class);
			break;
		default:
			intent = new Intent(this,BannerXmlActivity.class);
		}
		startActivity(intent);
	}
	
}
