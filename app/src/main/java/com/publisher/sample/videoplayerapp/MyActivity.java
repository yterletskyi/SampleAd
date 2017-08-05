package com.publisher.sample.videoplayerapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.publisher.sample.R;
import com.publisher.sample.sdk.OnCompleteListener;
import com.publisher.sample.sdk.Sdk;
import com.publisher.sample.sdk.VastCreator;
import com.publisher.sample.sdk.model.preload.response.PreloadResponse;
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

        mSdk = new Sdk(this);
    }

    private void initSdk() {
        IApiService apiService = ((VideoApp) getApplication()).getApiService();

        mSdk.initSdk(apiService, "5916309cb46f6b5a3e00009c", Arrays.asList("DEFAULT32590", "TESTREW28799", "TESTINT07107"), new OnCompleteListener<Boolean>() {
            @Override
            public void onCompleted(Boolean result) {
                if (result) {
                    System.out.println();
                    enablePreloadAdButton();
                } else {
                    Toast.makeText(MyActivity.this, "Init error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void enablePreloadAdButton() {
        findViewById(R.id.btn_preload).setEnabled(true);
    }

    private void preloadAd(String placementId) {
        mSdk.preloadAd(placementId, new OnCompleteListener<PreloadResponse>() {
            @Override
            public void onCompleted(PreloadResponse result) {
                formVastXml(result);
            }
        });
    }

    private void formVastXml(PreloadResponse result) {
        VastCreator vastCreator = new VastCreator();
        String xml = vastCreator.build(result);
        setVastXml(xml);
        enablePlayButton();
    }

    public void setVastXml(String xml) {
        mVideoFragment.setVastXml(xml);
    }

    public void enablePlayButton() {
        mVideoFragment.enablePlayButton();
    }

}
