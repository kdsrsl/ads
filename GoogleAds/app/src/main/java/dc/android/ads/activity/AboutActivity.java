/** 
 * Filename:    AboutActivity.java 
 * Description:  
 * Copyright:   Copyright (c)2016 
 * Company:     VCYBER 
 * @author:     senRsl senRsl@163.com 
 * @version:    1.0 
 * Create at:   2016年8月15日 下午3:11:52 
 * 
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年8月15日   senRsl      1.0         1.0 Version 
 */  
package dc.android.ads.activity;

import android.content.Intent;
import dc.android.common.activity.BaseAboutActivity;

/**
 * @author senrsl
 *
 */
public class AboutActivity extends BaseAboutActivity {

	/* (non-Javadoc)
	 * @see dc.android.common.activity.AboutActivity#jump()
	 */
	@Override
	protected void jump() {
		startActivity(new Intent(this,  HomeActivity.class));//senRslAds
		finish();
	}

}
