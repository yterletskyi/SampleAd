package com.google.ads.interactivemedia.v3.samples.videoplayerapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity {

    private VideoFragment mVideoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        mVideoFragment = (VideoFragment) supportFragmentManager.findFragmentById(R.id.video_fr);

    }

    public void setVideoUrl(String url) {
        mVideoFragment.setVideoUrl(url);
    }

    public void enablePlayButton() {
        mVideoFragment.enablePlayButton();
    }

}
