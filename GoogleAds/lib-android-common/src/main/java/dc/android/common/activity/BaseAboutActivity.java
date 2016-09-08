/** 
 * Filename:    AboutActivity.java 
 * Description:  
 * Copyright:   Copyright (c)2014 
 * Company:     bjwhds.com 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2014-8-11 下午4:02:38 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014-8-11      senRsl      1.0         1.0 Version 
 */  
package dc.android.common.activity;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import dc.android.common.R;


/**
 *
 *
 * @ClassName: AboutActivity
 * @author senRsl
 *
 * @Package: dc.bt.mono
 * @CreateTime: 2014-8-11 下午4:02:38
 *
 */
public abstract class BaseAboutActivity extends BaseActivity {
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_about);
		try {
			String version = getPackageManager().getPackageInfo(getPackageName()	,0).versionName;
			((TextView)findViewById(R.id.version)).setText(version);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			jumpThread();
		} catch (Exception e) {
			jump();
		}
	}
	
	private void jumpThread() throws Exception{
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				jump();
			}
		}, 1500);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		jump();
	}
	
	//senRsl,senRslPost
	protected abstract void jump();
//	{
//		startActivity(new Intent(AboutActivity.this,  SimulationSportActivity.class));
//		finish();
//	}
	
}
