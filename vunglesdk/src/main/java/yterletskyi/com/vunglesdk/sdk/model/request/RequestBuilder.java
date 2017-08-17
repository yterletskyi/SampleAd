package yterletskyi.com.vunglesdk.sdk.model.request;

import android.content.Context;
import android.os.Build;
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
import yterletskyi.com.vunglesdk.sdk.model.request.global.PreloadAdRequest;
import yterletskyi.com.vunglesdk.sdk.model.request.global.Vungle;
import yterletskyi.com.vunglesdk.sdk.model.request.reportad.Play;
import yterletskyi.com.vunglesdk.sdk.model.request.reportad.ReportAdRequest;
import yterletskyi.com.vunglesdk.sdk.model.request.willplayad.Placement;
import yterletskyi.com.vunglesdk.sdk.model.request.willplayad.WillPlayAdRequest;
import yterletskyi.com.vunglesdk.sdk.model.response.init.InitResponse;
import yterletskyi.com.vunglesdk.sdk.model.response.preload.Ad;
import yterletskyi.com.vunglesdk.sdk.utils.AppManager;
import yterletskyi.com.vunglesdk.sdk.utils.hardware.AndroidId;
import yterletskyi.com.vunglesdk.sdk.utils.hardware.BatteryManager;
import yterletskyi.com.vunglesdk.sdk.utils.hardware.Connectivity;
import yterletskyi.com.vunglesdk.sdk.utils.hardware.DiskSpaceManager;
import yterletskyi.com.vunglesdk.sdk.utils.hardware.MyAudioManager;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class RequestBuilder {

    private static final String PLATFORM = "android";
    private App mApp;
    private Device mDevice;

    public GlobalRequest buildInitSdkRequest(Context context, String appId, List<String> placementIds) {
        mApp = buildApp(context, appId);
        mDevice = buildDevice(context);
        return buildRequest(placementIds);
    }

    public GlobalRequest buildPreloadAdRequest(String placementId, InitResponse initResponse) {
        mDevice.ext.vungle.android.vduid = initResponse.vduid;
        return buildRequest(Collections.singletonList(placementId));
    }

    public GlobalRequest buildReportAdRequest(Ad adModel, boolean incentivized, long adDurationMillis, long videoLengthMillis) {
        GlobalRequest globalRequest = new GlobalRequest();
        long startTime = System.currentTimeMillis() - adDurationMillis;
        globalRequest
                .withApp(mApp)
                .withDevice(mDevice)
                .withRequest(
                        new ReportAdRequest()
                                .withTtDownload(0)
                                .withAdStartTime(startTime)
                                .withAppId(mApp.id)
                                .withCampaign(adModel.adMarkup.campaign)
                                .withAdDuration(adDurationMillis)
                                .withIncentivized(incentivized ? 1 : 0)
                                .withPlacementReferenceId(adModel.placementReferenceId)
                                .withAdToken(adModel.adMarkup.adToken)
                                .withUrl(adModel.adMarkup.url)
                                .withPlays(new Play()
                                        .withStartTime(startTime)
                                        .withVideoLength(videoLengthMillis)
                                        .withVideoViewed(videoLengthMillis)
                                )
                                .withAdType(adModel.adMarkup.adType)

                );
        return globalRequest;
    }

    public GlobalRequest buildPlayingAdRequest(String adToken, Placement placement) {
        GlobalRequest globalRequest = new GlobalRequest();
        globalRequest
                .withApp(mApp)
                .withDevice(mDevice)
                .withRequest(new WillPlayAdRequest()
                        .withAdToken(adToken)
                        .withPlacement(placement));
        return globalRequest;
    }

    private GlobalRequest buildRequest(List<String> placementIds) {
        GlobalRequest globalRequest = new GlobalRequest();
        try {
            globalRequest
                    .withApp(mApp)
                    .withDevice(mDevice)
                    .withRequest(
                            buildRequestWithMultiplePlacements(placementIds)
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return globalRequest;
    }

    private App buildApp(Context context, String appId) {
        return new App()
                .withId(appId)
                // TODO: 26.07.17 change it back
//                                .withBundle(context.getPackageName())
                .withBundle("com.publisher.sample")
                .withVer(new AppManager().getVersionName(context));
    }

    private Device buildDevice(Context context) {
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
                .withIfa(new AndroidId().getAndroidId(context))
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
                                                                .withSdCardAvailable(new DiskSpaceManager().externalMemoryAvailable() ? 1 : 0)
                                                                .withConnectionType(Connectivity.getConnectionType(context))
                                                                .withConnectionTypeDetail(Connectivity.getConnectionTypeDetail(context))
                                                                .withSoundEnabled(new MyAudioManager().isSoundEnabled(context) ? 1 : 0)
                                                                .withLanguage(Locale.getDefault().getLanguage())
                                                                .withLocale(Locale.getDefault().toString())
                                                                .withAndroidId(new AndroidId().getAndroidId(context))
                                                                .withVduid("")
                                                                .withOsName(Build.FINGERPRINT)
                                                                .withTimeZone(TimeZone.getDefault().getID())
                                                )
                                                .withPlatform(PLATFORM)
                                )
                );
    }

    private PreloadAdRequest buildRequestWithMultiplePlacements(List<String> placementIds) {
        return new PreloadAdRequest().withPlacements(placementIds);
    }

}
