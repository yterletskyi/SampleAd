package com.publisher.sample.sdk;

import android.content.Context;

import com.publisher.sample.sdk.model.init.response.InitResponse;
import com.publisher.sample.sdk.model.preload.response.Response;
import com.publisher.sample.sdk.model.request.GlobalRequest;
import com.publisher.sample.sdk.model.request.RequestBuilder;
import com.publisher.sample.videoplayerapp.api.IApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class Sdk {

    public static final String APP_VERSION = "1.0";
    public static final String VERSION = "5.0.0";

    private Context mContext;
    private IApiService mApiService;
    private String mAppId;

    public Sdk(Context context) {
        mContext = context;
    }

    public void initSdk(IApiService apiService, String appId, List<String> placementList, final OnCompleteListener<Boolean> listener) {
        mApiService = apiService;
        mAppId = appId;

        RequestBuilder requestBuilder = new RequestBuilder();
        GlobalRequest request = requestBuilder.buildInitSdkRequest(mContext, mAppId, placementList);

        Call<InitResponse> call = mApiService.initSDK(Sdk.VERSION, request);
        call.enqueue(new Callback<InitResponse>() {
            @Override
            public void onResponse(Call<InitResponse> call, retrofit2.Response<InitResponse> response) {
                listener.onCompleted(true);
            }

            @Override
            public void onFailure(Call<InitResponse> call, Throwable t) {
                listener.onCompleted(false);
            }
        });
    }

    public void preloadAd(String placementId) {
        RequestBuilder requestBuilder = new RequestBuilder();
        GlobalRequest request = requestBuilder.buildPreloadAdRequest(mContext, mAppId, placementId);

        Call<Response> responseCall = mApiService.preloadAd(request);
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                System.out.println();
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                System.out.println();
            }
        });
    }

}
