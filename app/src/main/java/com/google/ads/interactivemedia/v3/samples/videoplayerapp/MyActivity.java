package com.google.ads.interactivemedia.v3.samples.videoplayerapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.ads.interactivemedia.v3.samples.videoplayerapp.api.IApiService;
import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.play.response.InitResponse;
import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.requestad.GlobalRequest;
import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.responsead.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class MyActivity extends AppCompatActivity {

    private VideoFragment mVideoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        mVideoFragment = (VideoFragment) supportFragmentManager.findFragmentById(R.id.video_fr);

        findViewById(R.id.btn_preload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preloadAd();
            }
        });

        findViewById(R.id.btn_init).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSdk();
            }
        });

    }

    private void initSdk() {
        IApiService apiService = ((VideoApp) getApplication()).getApiService();

        GlobalRequest initSdkRequest = new GlobalRequest();
        initSdkRequest.request.placements.add("DEFAULT32590");
        initSdkRequest.request.placements.add("TESTREW28799");
        initSdkRequest.request.placements.add("TESTINT07107");

        Call<InitResponse> call = apiService.initSDK("5.0.0", initSdkRequest);
        call.enqueue(new Callback<InitResponse>() {
            @Override
            public void onResponse(Call<InitResponse> call, retrofit2.Response<InitResponse> response) {
                System.out.println();
            }

            @Override
            public void onFailure(Call<InitResponse> call, Throwable t) {
                System.out.println();
            }
        });

    }

    private void preloadAd() {
        IApiService apiService = ((VideoApp) getApplication()).getApiService();

        Call<Response> responseCall = apiService.preloadAd(new GlobalRequest());
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                String url = response.body().ads.get(0).adMarkup.url;
                setVideoUrl(url);
                enablePlayButton();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MyActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
