package dc.android.ads.activity;

import dc.android.ads.R;
import dc.android.common.activity.BaseActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.vending.billing.IInAppBillingService;

import java.util.Collections;
import java.util.List;

/**
 * 第三，admob插页式奖励
 * Created by senrsl on 16-9-7.
 */

public class AdmobInAppPurchaseActivity extends BaseActivity implements PlayStorePurchaseListener {

    public static final int BILLING_RESPONSE_RESULT_OK = 0;

    private InterstitialAd interstitial;
    private IInAppBillingService mService;
    private Button showAdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_inapppurchase);
        showAdButton = (Button) findViewById(R.id.showAd);
        showAdButton.setEnabled(false);

        // Create the interstitial ad and set Google Play store purchase parameters.
        interstitial = new InterstitialAd(this);
        // Set the PlayStorePurchaseListener. The developer gets the public key when uploading an apk to
        // the Google Play store. The SDK uses this public key to verify the purchase came from this
        // app. If the public key is null, the SDK will not verify the purchase.
        interstitial.setPlayStorePurchaseParams(this, null);

        // TODO: put your ad unit id here.
        interstitial.setAdUnitId(getString(R.string.ad_unit_id_in_app_purchase));

        setInterstitialAdListener();
    }

    private void setInterstitialAdListener() {
        // Set an AdListener.
        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                showAdButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                showAdButton.setEnabled(false);
                // Optional: your custom code here.
            }
        });
    }

    public void loadInterstitial(@SuppressWarnings("unused") View v) {
        // Create ad request.
        AdRequest adRequest = new AdRequest.Builder().build();

        // Begin loading interstitial ad.
        interstitial.loadAd(adRequest);
    }

    public void displayInterstitial(@SuppressWarnings("unused") View v) {
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    @Override
    public boolean isValidPurchase(String sku) {
        // Optional: check if the product has already been purchased.
        try {
            if (getOwnedProducts().contains(sku)) {
                // Handle the case if product is already purchased.
                return false;
            }
        } catch (RemoteException e) {
            Log.e("Iap-Ad", "Query purchased product failed.", e);
            return false;
        }
        return true;
    }

    @Override
    public void onInAppPurchaseFinished(InAppPurchaseResult result) {
        Log.i("Iap-Ad", "onInAppPurchaseFinished Start");
        int resultCode = result.getResultCode();
        Log.i("Iap-Ad", "result code: " + resultCode);
        String sku = result.getProductId();
        if (resultCode == Activity.RESULT_OK) {
            Log.i("Iap-Ad", "purchased product id: " + sku);
            int responseCode = result.getPurchaseData().getIntExtra(
                    "RESPONSE_CODE", BILLING_RESPONSE_RESULT_OK);
            String purchaseData = result.getPurchaseData().getStringExtra("INAPP_PURCHASE_DATA");
            Log.i("Iap-Ad", "response code: " + responseCode);
            Log.i("Iap-Ad", "purchase data: " + purchaseData);

            // Finish purchase and consume product.
            result.finishPurchase();
            // if (responseCode == BILLING_RESPONSE_RESULT_OK) {
            // Optional: your custom process goes here, e.g., add coins after purchase.
            //  }
        } else {
            Log.w("Iap-Ad", "Failed to purchase product: " + sku);
        }
        Log.i("Iap-Ad", "onInAppPurchaseFinished End");
    }

    private List<String> getOwnedProducts() throws RemoteException {
        // Query for purchased items.
        // See http://developer.android.com/google/play/billing/billing_reference.html and
        // http://developer.android.com/google/play/billing/billing_integrate.html
        Bundle ownedItems = mService.getPurchases(3, getPackageName(), "inapp", null);
        int response = ownedItems.getInt("RESPONSE_CODE");
        Log.i("Iap-Ad", "Response code of purchased item query");
        if (response == 0) {
            return ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
        }
        return Collections.emptyList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
