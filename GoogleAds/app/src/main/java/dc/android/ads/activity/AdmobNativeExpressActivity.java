package dc.android.ads.activity;

import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;

import dc.android.ads.R;
import dc.android.common.activity.BaseActivity;

/**
 * 第四个，原生广告
 * Created by senrsl on 16-9-8.
 */

public class AdmobNativeExpressActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_native_express);

        NativeExpressAdView adViewSmall = (NativeExpressAdView) findViewById(R.id.adViewSmall);
        adViewSmall.loadAd(new AdRequest.Builder().build());
        NativeExpressAdView adViewMedium = (NativeExpressAdView) findViewById(R.id.adViewMedium);
        adViewMedium.loadAd(new AdRequest.Builder().build());
        NativeExpressAdView adViewLarge = (NativeExpressAdView) findViewById(R.id.adViewLarge);
        adViewLarge.loadAd(new AdRequest.Builder().build());
    }
}
