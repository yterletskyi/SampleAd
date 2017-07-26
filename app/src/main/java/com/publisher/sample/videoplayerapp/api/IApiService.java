package com.publisher.sample.videoplayerapp.api;

import com.publisher.sample.sdk.model.request.GlobalRequest;
import com.publisher.sample.sdk.model.init.response.InitResponse;
import com.publisher.sample.sdk.model.preload.response.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by yterletskyi on 25.07.17.
 */

public interface IApiService {

    @Headers("Content-Type: application/json")

    @POST("https://ads.api.vungle.com/config")
    Call<InitResponse> initSDK(@Header("Vungle-Version") String value, @Body GlobalRequest request);

    @POST("/api/v5/ads")
    Call<Response> preloadAd(@Body GlobalRequest request);


}
