package com.google.ads.interactivemedia.v3.samples.videoplayerapp.api;

import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.requestad.RequestAd;
import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.responsead.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by yterletskyi on 25.07.17.
 */

public interface IApiService {

    @Headers("Content-Type: application/json")

    @POST("/api/v5/ads")
    Call<Response> preloadAd(@Body RequestAd requestAd);


}
