package com.publisher.sample.videoplayerapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.publisher.sample.R;

import java.util.Arrays;
import java.util.List;

import yterletskyi.com.vunglesdk.sdk.OnAdListener;
import yterletskyi.com.vunglesdk.sdk.OnInitListener;
import yterletskyi.com.vunglesdk.sdk.Sdk;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BasicExample";

    private static final String APP_ID = "5916309cb46f6b5a3e00009c";
    private static final List<String> PLACEMENTS = Arrays.asList("DEFAULT32590", "TESTREW28799", "TESTINT07107");
    private Sdk mSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_init).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSdk();
            }
        });

        findViewById(R.id.btn_preload_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSdk.preloadAd("TESTREW28799", new OnAdListener() {
                    @Override
                    public void onAdLoaded() {
                        Log.i(TAG, "onAdLoaded: ");
                        enableButton(R.id.btn_play_one);
                    }

                    @Override
                    public void onAdStarted() {
                        Log.i(TAG, "onAdStarted: ");
                    }

                    @Override
                    public void onAdClosed() {
                        Log.i(TAG, "onAdClosed: ");
                    }

                    @Override
                    public void onAdFailedToLoad() {
                        Log.i(TAG, "onAdFailedToLoad: ");
                    }

                    @Override
                    public void onAdCompleted() {
                        Log.i(TAG, "onAdCompleted: ");
                    }
                });
            }
        });

        findViewById(R.id.btn_play_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSdk.playAd(MainActivity.this, "TESTREW28799");
            }
        });

        findViewById(R.id.btn_preload_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSdk.preloadAd("TESTINT07107", new OnAdListener() {
                    @Override
                    public void onAdLoaded() {
                        Log.i(TAG, "onAdLoaded: ");
                        enableButton(R.id.btn_play_two);
                    }

                    @Override
                    public void onAdStarted() {
                        Log.i(TAG, "onAdStarted: ");
                    }

                    @Override
                    public void onAdClosed() {
                        Log.i(TAG, "onAdClosed: ");
                    }

                    @Override
                    public void onAdFailedToLoad() {
                        Log.i(TAG, "onAdFailedToLoad: ");
                    }

                    @Override
                    public void onAdCompleted() {
                        Log.i(TAG, "vastComplete: ");
                    }
                });
            }
        });

        findViewById(R.id.btn_play_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSdk.playAd(MainActivity.this, "TESTINT07107");
            }
        });

        Context applicationContext = getApplicationContext();
        mSdk = Sdk.getInstance(applicationContext);
    }

    private void initSdk() {
        mSdk.initSdk(APP_ID, PLACEMENTS, new OnInitListener() {

            @Override
            public void onInitSucceeded() {
                enableButton(R.id.btn_preload_one);
                enableButton(R.id.btn_preload_two);
            }

            @Override
            public void onInitFailed() {
                Toast.makeText(MainActivity.this, "Init error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void enableButton(@IdRes int btnId) {
        findViewById(btnId).setEnabled(true);
    }

}