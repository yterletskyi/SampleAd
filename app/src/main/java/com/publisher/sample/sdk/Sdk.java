package com.publisher.sample.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.webkit.WebView;

import com.publisher.sample.sdk.model.request.App;
import com.publisher.sample.sdk.model.request.Device;
import com.publisher.sample.sdk.model.request.Ext;
import com.publisher.sample.sdk.model.request.GlobalRequest;
import com.publisher.sample.sdk.model.request.Request;
import com.publisher.sample.sdk.model.request.Vungle;
import com.publisher.sample.sdk.model.request.Android;
import com.publisher.sample.sdk.model.init.response.InitResponse;
import com.publisher.sample.sdk.model.preload.response.Response;
import com.publisher.sample.videoplayerapp.api.IApiService;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
                        new Device()
                                .withUa(new WebView(mActivity).getSettings().getUserAgentString())
                                .withLmt(1)
                                .withMake(Build.MANUFACTURER)
                                .withModel(Build.MODEL)
                                .withOs("android")
                                .withOsv(Build.VERSION.RELEASE)
                                .withW(mActivity.getResources().getDisplayMetrics().widthPixels)
                                .withH(mActivity.getResources().getDisplayMetrics().heightPixels)
                                .withCarrier(((TelephonyManager) mActivity.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperatorName())
                                .withIfa(Settings.Secure.getString(mActivity.getContentResolver(), Settings.Secure.ANDROID_ID))
                                .withExt(
                                        new Ext()
                                                .withVungle(
                                                        new Vungle()
                                                                .withAndroid(
                                                                        new Android()
                                                                                .withVolumeLevel(new MyAudioManager().getVolumeLevel(mActivity))
                                                                                .withBatteryLevel(new BatteryManager().getBatteryPercentage(mActivity))
                                                                                .withBatterySaverEnabled(new BatteryManager().getBatterySaverEnabled(mActivity))
                                                                                .withBatteryState(new BatteryManager().isCharging(mActivity) ? "CHARGING" : "NOT_CHARGING")
                                                                                .withStorageBytesAvailable(new DiskSpaceManager().getAvailableExternalMemorySize())
                                                                                .withDataSaverStatus(Connectivity.getDataSavedStatus(mActivity))
                                                                                .withNetworkMetered(Connectivity.isNetworkMetered(mActivity) ? 1 : 0)
                                                                                .withSdCardAvailable(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED) ? 1 : 0)
                                                                                .withConnectionType(Connectivity.getConnectionType(mActivity))
                                                                                .withConnectionTypeDetail(Connectivity.getConnectionTypeDetail(mActivity))
                                                                                .withSoundEnabled(new MyAudioManager().isSoundEnabled(mActivity) ? 1 : 0)
                                                                                .withLanguage(Locale.getDefault().getLanguage())
                                                                                .withLocale(Locale.getDefault().toString())
                                                                                .withAndroidId(Settings.Secure.getString(mActivity.getContentResolver(), Settings.Secure.ANDROID_ID))
                                                                                .withVduid("")
                                                                                .withOsName(Build.FINGERPRINT)
                                                                                .withTimeZone(TimeZone.getDefault().getID())
                                                                )
                                                                .withPlatform("android")
                                                )
                                )


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
