package com.google.ads.interactivemedia.v3.samples.videoplayerapp.sdk;

import com.google.ads.interactivemedia.v3.samples.videoplayerapp.api.IApiService;
import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.play.response.InitResponse;
import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.requestad.GlobalRequest;
import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.responsead.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class Sdk {

    public static final String VERSION = "5.0.0";

    private IApiService mApiService;

    public void initSdk(IApiService apiService, String appId, List<String> placementList, final OnCompleteListener<Boolean> listener) {
        mApiService = apiService;
        GlobalRequest initSdkRequest = new GlobalRequest();
        initSdkRequest.request.placements = placementList;
        initSdkRequest.app.id = appId;

        Call<InitResponse> call = mApiService.initSDK(Sdk.VERSION, initSdkRequest);
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
        GlobalRequest globalRequest = new GlobalRequest();
        globalRequest.request.placements.add(placementId);

        Call<Response> responseCall = mApiService.preloadAd(globalRequest);

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
