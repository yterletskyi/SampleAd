package yterletskyi.com.vunglesdk.sdk.model.request;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.webkit.WebView;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import yterletskyi.com.vunglesdk.sdk.model.request.global.Android;
import yterletskyi.com.vunglesdk.sdk.model.request.global.App;
import yterletskyi.com.vunglesdk.sdk.model.request.global.Device;
import yterletskyi.com.vunglesdk.sdk.model.request.global.Ext;
import yterletskyi.com.vunglesdk.sdk.model.request.global.GlobalRequest;
import yterletskyi.com.vunglesdk.sdk.model.request.global.Request;
import yterletskyi.com.vunglesdk.sdk.model.request.global.Vungle;
import yterletskyi.com.vunglesdk.sdk.model.response.init.InitResponse;
import yterletskyi.com.vunglesdk.sdk.model.request.willplayad.Placement;
import yterletskyi.com.vunglesdk.sdk.utils.hardware.BatteryManager;
import yterletskyi.com.vunglesdk.sdk.utils.hardware.Connectivity;
import yterletskyi.com.vunglesdk.sdk.utils.hardware.DiskSpaceManager;
import yterletskyi.com.vunglesdk.sdk.utils.hardware.MyAudioManager;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class RequestBuilder {

    private static final String PLATFORM = "android";

    public GlobalRequest buildInitSdkRequest(Context context, String appId, List<String> placementIds) {
        return buildRequest(context, appId, placementIds, "");
    }

    public GlobalRequest buildPreloadAdRequest(Context context, String appId, String placementId, InitResponse initResponse) {
        return buildRequest(context, appId, Collections.singletonList(placementId), initResponse.vduid);
    }

    public GlobalRequest buildPlayingAdRequest(Context context, String appId, String adToken, Placement placement) {
        GlobalRequest globalRequest = new GlobalRequest();
        try {
            globalRequest
                    .withApp(
                            buildApp(context, appId)
                    )
                    .withDevice(
                            buildDevice(context, appId)
                    )
                    .withRequest(new yterletskyi.com.vunglesdk.sdk.model.request.willplayad.Request()
                            .withAdToken(adToken)
                            .withPlacement(placement));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return globalRequest;
    }

    private GlobalRequest buildRequest(Context context, String appId, List<String> placementIds, String vduid) {
        GlobalRequest globalRequest = new GlobalRequest();
        try {
            globalRequest
                    .withApp(
                            buildApp(context, appId)
                    )
                    .withRequest(
                            buildRequestWithMultiplePlacements(placementIds)
                    )
                    .withDevice(
                            buildDevice(context, vduid)
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return globalRequest;
    }

    private App buildApp(Context context, String appId) throws Exception {
        return new App()
                .withId(appId)
                // TODO: 26.07.17 change it back
//                                .withBundle(context.getPackageName())
                .withBundle("com.publisher.sample")
                .withVer(getVersionName(context));
    }

    private Device buildDevice(Context context, String vduid) throws Exception {
        return new Device()
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
                                                                .withVduid(vduid)
                                                                .withOsName(Build.FINGERPRINT)
                                                                .withTimeZone(TimeZone.getDefault().getID())
                                                )
                                                .withPlatform(PLATFORM)
                                )
                );
    }

    private Request buildRequestWithMultiplePlacements(List<String> placementIds) throws Exception {
        return new Request()
                .withPlacements(placementIds);
    }

    private String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    private String getVersionName(Context context) {
        String version;
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = "1.0";
        }
        return version;
    }

}
