package com.publisher.sample.videoplayerapp;

import android.app.Application;

import com.publisher.sample.videoplayerapp.api.IApiService;
import com.publisher.sample.videoplayerapp.api.LoggingInterceptor;

import okhttp3.OkHttpClient;
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

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.vungle.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = mRetrofit.create(IApiService.class);
    }

    public IApiService getApiService() {
        return mApiService;
    }
}
