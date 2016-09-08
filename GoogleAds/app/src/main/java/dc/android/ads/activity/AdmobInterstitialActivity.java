package dc.android.ads.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import dc.android.ads.R;
import dc.android.common.activity.BaseActivity;
import dc.common.Logger;

/**
 * 第二个，插页式
 * Created by senrsl on 16-9-7.
 */

public class AdmobInterstitialActivity extends BaseActivity {

    //下面是github的例子，可以用
//    private static final long GAME_LENGTH_MILLISECONDS = 3000;
//
//    private InterstitialAd mInterstitialAd;
//    private CountDownTimer mCountDownTimer;
//    private Button mRetryButton;
//    private boolean mGameIsInProgress;
//    private long mTimerMilliseconds;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_admob_interstitial);
//
//
//        // Initialize the Mobile Ads SDK.
//        MobileAds.initialize(this, "ca-app-pub-5714897956607268~9126010835");
//
//        // Create the InterstitialAd and set the adUnitId.
//        mInterstitialAd = new InterstitialAd(this);
//        // Defined in res/values/strings.xml
//        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id_interstitial));
//
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                startGame();
//            }
//        });
//
//        // Create the "retry" button, which tries to show an interstitial between game plays.
//        mRetryButton = ((Button) findViewById(R.id.btn_retry));
//        mRetryButton.setVisibility(View.INVISIBLE);
//        mRetryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showInterstitial();
//            }
//        });
//
//        startGame();
//    }
//
//    private void createTimer(final long milliseconds) {
//        // Create the game timer, which counts down to the end of the level
//        // and shows the "retry" button.
//        if (mCountDownTimer != null) {
//            mCountDownTimer.cancel();
//        }
//
//        final TextView textView = ((TextView) findViewById(R.id.timer));
//
//        mCountDownTimer = new CountDownTimer(milliseconds, 50) {
//            @Override
//            public void onTick(long millisUnitFinished) {
//                mTimerMilliseconds = millisUnitFinished;
//                textView.setText("seconds remaining: " + ((millisUnitFinished / 1000) + 1));
//            }
//
//            @Override
//            public void onFinish() {
//                mGameIsInProgress = false;
//                textView.setText("done!");
//                mRetryButton.setVisibility(View.VISIBLE);
//            }
//        };
//    }
//
//    @Override
//    public void onResume() {
//        // Start or resume the game.
//        super.onResume();
//
//        if (mGameIsInProgress) {
//            resumeGame(mTimerMilliseconds);
//        }
//    }
//
//    @Override
//    public void onPause() {
//        // Cancel the timer if the game is paused.
//        mCountDownTimer.cancel();
//        super.onPause();
//    }
//
//    private void showInterstitial() {
//        // Show the ad if it's ready. Otherwise toast and restart the game.
//        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
//            startGame();
//        }
//    }
//
//    private void startGame() {
//        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
//        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
//            AdRequest adRequest = new AdRequest.Builder().build();
//            mInterstitialAd.loadAd(adRequest);
//        }
//
//        mRetryButton.setVisibility(View.INVISIBLE);
//        resumeGame(GAME_LENGTH_MILLISECONDS);
//    }
//
//    private void resumeGame(long milliseconds) {
//        // Create a new timer for the correct length and start it.
//        mGameIsInProgress = true;
//        mTimerMilliseconds = milliseconds;
//        createTimer(milliseconds);
//        mCountDownTimer.start();
//    }


    //下面是网站教程的例子，也可以用
    InterstitialAd mInterstitialAd;
    Button mNewGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admob_interstitial);

        mNewGameButton = (Button) findViewById(R.id.btn_retry);

        MobileAds.initialize(this, "ca-app-pub-5714897956607268~9126010835");//这句话到底是干嘛的

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id_interstitial));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                beginPlayingGame();
            }
        });

        requestNewInterstitial();

        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    beginPlayingGame();
                }
            }
        });

        beginPlayingGame();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void beginPlayingGame() {
        // Play for a while, then display the New Game Button
        Logger.w(this,getString(R.string.app_name));
    }
}
