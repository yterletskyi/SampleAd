package com.publisher.sample.videoplayerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.publisher.sample.R;

import yterletskyi.com.vunglesdk.sdk.Sdk;

/**
 * Created by yterletskyi on 15.08.17.
 */

public class NewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        findViewById(R.id.btn_new_screen_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAd();
            }
        });

    }

    private void playAd() {
        Context applicationContext = getApplicationContext();
        Sdk sdk = Sdk.getInstance(applicationContext);
        sdk.playAd(this, "TESTREW28799");
    }
}
