package com.publisher.sample.sdk.model.request;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.webkit.WebView;

import com.publisher.sample.sdk.model.utils.BatteryManager;
import com.publisher.sample.sdk.model.utils.Connectivity;
import com.publisher.sample.sdk.model.utils.DiskSpaceManager;
import com.publisher.sample.sdk.model.utils.MyAudioManager;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.publisher.sample.sdk.Sdk.APP_VERSION;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class RequestBuilder {

    private static final String PLATFORM = "android";

    public GlobalRequest buildInitSdkRequest(Context context, String appId, List<String> placementIds) {
        return buildRequest(context, appId, placementIds);
    }

    public GlobalRequest buildPreloadAdRequest(Context context, String appId, String placementId) {
        return buildRequest(context, appId, Collections.singletonList(placementId));
    }


    private GlobalRequest buildRequest(Context context, String appId, List<String> placementIds) {
        return new GlobalRequest()
                .withApp(
                        new App()
                                .withId(appId)
                                // TODO: 26.07.17 change it back
                                .withBundle("com.publisher.sample")
                                .withVer(APP_VERSION)
                )
                .withRequest(
                        new Request()
                                .withPlacements(placementIds)
                )
                .withDevice(
                        new Device()
                                .withUa(new WebView(context).getSettings().getUserAgentString())
                                .withLmt(1)
                                .withMake(Build.MANUFACTURER)
                                .withModel(Build.MODEL)
                                .withOs(PLATFORM)
                                .withOsv(Build.VERSION.RELEASE)
                                .withW(context.getResources().getDisplayMetrics().widthPixels)
                                .withH(context.getResources().getDisplayMetrics().heightPixels)
                                .withCarrier(((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkOperatorName())
                                .withIfa(getAndroidId(context))
                                .withExt(
                                        new Ext()
                                                .withVungle(
                                                        new Vungle()
                                                                .withAndroid(
                                                                        new Android()
                                                                                .withVolumeLevel(new MyAudioManager().getVolumeLevel(context))
                                                                                .withBatteryLevel(new BatteryManager().getBatteryPercentage(context))
                                                                                .withBatterySaverEnabled(new BatteryManager().getBatterySaverEnabled(context))
                                                                                .withBatteryState(new BatteryManager().isCharging(context) ? "CHARGING" : "NOT_CHARGING")
                                                                                .withStorageBytesAvailable(new DiskSpaceManager().getAvailableExternalMemorySize())
                                                                                .withDataSaverStatus(Connectivity.getDataSavedStatus(context))
                                                                                .withNetworkMetered(Connectivity.isNetworkMetered(context) ? 1 : 0)
                                                                                .withSdCardAvailable(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED) ? 1 : 0)
                                                                                .withConnectionType(Connectivity.getConnectionType(context))
                                                                                .withConnectionTypeDetail(Connectivity.getConnectionTypeDetail(context))
                                                                                .withSoundEnabled(new MyAudioManager().isSoundEnabled(context) ? 1 : 0)
                                                                                .withLanguage(Locale.getDefault().getLanguage())
                                                                                .withLocale(Locale.getDefault().toString())
                                                                                .withAndroidId(getAndroidId(context))
                                                                                .withVduid("")
                                                                                .withOsName(Build.FINGERPRINT)
                                                                                .withTimeZone(TimeZone.getDefault().getID())
                                                                )
                                                                .withPlatform(PLATFORM)
                                                )
                                )
                );
    }

    private String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
