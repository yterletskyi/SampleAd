package com.publisher.sample.videoplayerapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.publisher.sample.R;
import com.publisher.sample.sdk.OnCompleteListener;
import com.publisher.sample.sdk.Sdk;
import com.publisher.sample.videoplayerapp.api.IApiService;

import java.util.Arrays;

public class MyActivity extends AppCompatActivity {

    private Sdk mSdk;
    private VideoFragment mVideoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        mVideoFragment = (VideoFragment) supportFragmentManager.findFragmentById(R.id.video_fr);

        findViewById(R.id.btn_init).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSdk();
            }
        });

        findViewById(R.id.btn_preload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preloadAd("TESTREW28799");
            }
        });
    }

    private void preloadAd(String placementId) {
        mSdk.preloadAd(placementId);
    }

    private void initSdk() {
        IApiService apiService = ((VideoApp) getApplication()).getApiService();

        mSdk = new Sdk(this);
        mSdk.initSdk(apiService, "5916309cb46f6b5a3e00009c", Arrays.asList("DEFAULT32590", "TESTREW28799", "TESTINT07107"), new OnCompleteListener<Boolean>() {
            @Override
            public void onCompleted(Boolean result) {
                if (result) {
                    System.out.println();
                } else {
                    Toast.makeText(MyActivity.this, "Init error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setVideoUrl(String url) {
        mVideoFragment.setVideoUrl(url);
    }

    public void enablePlayButton() {
        mVideoFragment.enablePlayButton();
    }

}
