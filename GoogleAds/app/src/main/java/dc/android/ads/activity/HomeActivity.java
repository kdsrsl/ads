package dc.android.ads.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import dc.android.ads.R;
import dc.android.common.activity.BaseActivity;

/**
 * @author senrsl
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            startActivity(new Intent(HomeActivity.this,AboutActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void click(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_admob_bannner:
                startActivity(new Intent(HomeActivity.this,AdmobBannerActivity.class));
                break;
            case R.id.btn_admob_intersititial:
                startActivity(new Intent(HomeActivity.this,AdmobInterstitialActivity.class));
                break;
            case R.id.btn_admob_inapppurchase:
                startActivity(new Intent(HomeActivity.this,AdmobInAppPurchaseActivity.class));
                break;
            case R.id.btn_admob_native_express:
                intent = new Intent(HomeActivity.this,AdmobNativeExpressActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_admob_native_advance:
                intent = new Intent(HomeActivity.this,AdmobNativeAdvancedActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_admob_rewarded_video:
                intent = new Intent(HomeActivity.this,AdmobRewardedVideoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_doubleclick_banner:
                intent = new Intent(HomeActivity.this,DoubleclickBannerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_doubleclick_interstitial:
                intent = new Intent(HomeActivity.this,DoubleclickInterstitialActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_doubleclick_native:
                intent = new Intent(HomeActivity.this,DoubleclickNativeActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
