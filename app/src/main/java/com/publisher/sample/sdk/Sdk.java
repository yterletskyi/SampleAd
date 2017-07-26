package com.publisher.sample.sdk;

import android.app.Activity;

import com.publisher.sample.sdk.model.App;
import com.publisher.sample.sdk.model.Device;
import com.publisher.sample.sdk.model.GlobalRequest;
import com.publisher.sample.sdk.model.Request;
import com.publisher.sample.sdk.model.init.response.InitResponse;
import com.publisher.sample.sdk.model.preload.responsead.Response;
import com.publisher.sample.videoplayerapp.api.IApiService;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class Sdk {

    public static final String APP_VERSION = "1.0";
    public static final String VERSION = "5.0.0";

    public Activity mActivity;
    private IApiService mApiService;
    private String mAppId;

    public Sdk(Activity activity) {
        mActivity = activity;
    }

    public void initSdk(IApiService apiService, String appId, List<String> placementList, final OnCompleteListener<Boolean> listener) {
        mApiService = apiService;
        mAppId = appId;
        GlobalRequest initSdkRequest = new GlobalRequest()
                .withApp(
                        new App()
                                .withId(mAppId)
                                .withBundle(mActivity.getPackageName())
                                .withVer(APP_VERSION)
                )
                .withRequest(
                        new Request()
                                .withPlacements(placementList)
                )
                .withDevice(
                        // TODO: 26.07.17 finish this
                        new Device()
                );

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
        GlobalRequest globalRequest = new GlobalRequest()
                .withApp(
                        new App()
                                .withId(mAppId)
                                .withBundle(mActivity.getPackageName())
                                .withVer(APP_VERSION)
                )
                .withRequest(
                        new Request()
                                .withPlacements(Collections.singletonList(placementId))
                )
                .withDevice(
                        // TODO: 26.07.17 finish this
                        new Device()
                );

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
