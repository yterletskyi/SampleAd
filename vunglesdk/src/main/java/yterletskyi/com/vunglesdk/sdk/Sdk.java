package yterletskyi.com.vunglesdk.sdk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.nexage.sourcekit.vast.VASTPlayer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yterletskyi.com.vunglesdk.sdk.api.IApiService;
import yterletskyi.com.vunglesdk.sdk.model.init.response.InitResponse;
import yterletskyi.com.vunglesdk.sdk.model.preload.response.PreloadResponse;
import yterletskyi.com.vunglesdk.sdk.model.request.GlobalRequest;
import yterletskyi.com.vunglesdk.sdk.model.request.RequestBuilder;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class Sdk {

    public static final String APP_VERSION = "1.0";
    private static final String VERSION = "5.0.0";
    private static final String TAG = "VungleSdk";
    private static final String API_ENDPOINT = "https://api.vungle.com";
    private String mAppId;
    private Context mContext;
    private IApiService mApiService;
    private OnAdListener mOnAdListener;
    private String mVastXml;
    private VASTPlayer mVASTPlayer;

    private InitResponse mInitResponse;

    public Sdk(Context context) {
        mContext = context;
        createApiInterface();
    }

    private void createApiInterface() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(IApiService.class);
    }

    public void initSdk(String appId, List<String> placementList, final OnInitListener listener) {
        mAppId = appId;

        RequestBuilder requestBuilder = new RequestBuilder();
        GlobalRequest request = requestBuilder.buildInitSdkRequest(mContext, mAppId, placementList);

        Call<InitResponse> call = mApiService.initSDK(Sdk.VERSION, request);
        call.enqueue(new Callback<InitResponse>() {
            @Override
            public void onResponse(@NonNull Call<InitResponse> call, @NonNull retrofit2.Response<InitResponse> response) {
                mInitResponse = response.body();
                listener.onInitSucceeded();
            }

            @Override
            public void onFailure(@NonNull Call<InitResponse> call, @NonNull Throwable t) {
                listener.onInitFailed();
            }
        });
    }

    public void preloadAd(String placementId, OnAdListener onAdListener) {
        mOnAdListener = onAdListener;
        RequestBuilder requestBuilder = new RequestBuilder();
        GlobalRequest request = requestBuilder.buildPreloadAdRequest(mContext, mAppId, placementId, mInitResponse);

        Call<PreloadResponse> responseCall = mApiService.preloadAd(request);
        responseCall.enqueue(new Callback<PreloadResponse>() {
            @Override
            public void onResponse(@NonNull Call<PreloadResponse> call, @NonNull retrofit2.Response<PreloadResponse> response) {
                PreloadResponse result = response.body();
                formVastXml(result);
                mOnAdListener.onAdLoaded();
            }

            @Override
            public void onFailure(@NonNull Call<PreloadResponse> call, @NonNull Throwable t) {
                mOnAdListener.onAdFailedToLoad();
            }
        });
    }

    private void formVastXml(PreloadResponse result) {
        VastCreator vastCreator = new VastCreator();
        mVastXml = vastCreator.build(result);
    }

    public void playAd() {
        mVASTPlayer = new VASTPlayer(mContext, new VASTPlayer.VASTPlayerListener() {
            @Override
            public void vastReady() {
                mVASTPlayer.play();
                mOnAdListener.onAdStarted();
            }

            @Override
            public void vastError(int error) {
                mOnAdListener.onAdFailedToLoad();
            }

            @Override
            public void vastClick() {
                Log.i(TAG, "vastClick: ");
            }

            @Override
            public void vastComplete() {
                mOnAdListener.onAdCompleted();
            }

            @Override
            public void vastDismiss() {
                mOnAdListener.onAdClosed();
            }
        });
        mVASTPlayer.loadVideoWithData(mVastXml);
    }

}
