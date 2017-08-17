package yterletskyi.com.vunglesdk.sdk.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;
import yterletskyi.com.vunglesdk.sdk.model.response.init.InitResponse;
import yterletskyi.com.vunglesdk.sdk.model.response.preload.PreloadResponse;
import yterletskyi.com.vunglesdk.sdk.model.response.reportad.ReportAdResponse;
import yterletskyi.com.vunglesdk.sdk.model.request.global.GlobalRequest;
import yterletskyi.com.vunglesdk.sdk.model.request.willplayad.WillPlayAdRequest;
import yterletskyi.com.vunglesdk.sdk.model.response.willplayad.WillPlayAdResponse;

/**
 * Created by yterletskyi on 25.07.17.
 */

public interface IApiService {

    @POST("https://ads.api.vungle.com/config")
    @Headers("Content-Type: application/json")
    Call<InitResponse> initSDK(@Header("Vungle-Version") String value, @Body GlobalRequest request);

    @POST // ads endpoint
    @Headers("Content-Type: application/json")
    Call<PreloadResponse> preloadAd(@Url String url, @Body GlobalRequest request);

    @GET
    Call<Void> sendGetRequest(@Url String url);

    @POST // will_play_ad endpoint
    @Headers("Content-Type: application/json")
    Call<WillPlayAdResponse> willPlayAd(@Url String url, @Body GlobalRequest request);

    @POST // report_ad endpoint
    @Headers("Content-Type: application/json")
    Call<ReportAdResponse> reportAd(@Url String url, @Body WillPlayAdRequest willPlayAdRequest);

}
