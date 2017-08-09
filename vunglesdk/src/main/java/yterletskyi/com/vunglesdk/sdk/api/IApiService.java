package yterletskyi.com.vunglesdk.sdk.api;

import yterletskyi.com.vunglesdk.sdk.model.init.response.InitResponse;
import yterletskyi.com.vunglesdk.sdk.model.preload.response.PreloadResponse;
import yterletskyi.com.vunglesdk.sdk.model.request.GlobalRequest;

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

    @Headers("Content-Type: application/json")
    @POST("https://api.vungle.com/api/v5/ads")
    Call<PreloadResponse> preloadAd(@Body GlobalRequest request);


}
