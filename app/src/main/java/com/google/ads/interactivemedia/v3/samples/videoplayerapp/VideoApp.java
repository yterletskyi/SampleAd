package com.google.ads.interactivemedia.v3.samples.videoplayerapp;

import android.app.Application;

import com.google.ads.interactivemedia.v3.samples.videoplayerapp.api.IApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yterletskyi on 25.07.17.
 */

public class VideoApp extends Application {

    private IApiService mApiService;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.vungle.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = mRetrofit.create(IApiService.class);
    }

    public IApiService getApiService() {
        return mApiService;
    }
}
