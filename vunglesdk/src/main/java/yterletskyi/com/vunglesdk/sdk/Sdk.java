package yterletskyi.com.vunglesdk.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import org.nexage.sourcekit.vast.VASTPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yterletskyi.com.vunglesdk.sdk.api.IApiService;
import yterletskyi.com.vunglesdk.sdk.model.request.RequestBuilder;
import yterletskyi.com.vunglesdk.sdk.model.request.global.GlobalRequest;
import yterletskyi.com.vunglesdk.sdk.model.request.willplayad.Placement;
import yterletskyi.com.vunglesdk.sdk.model.request.willplayad.WillPlayAdRequest;
import yterletskyi.com.vunglesdk.sdk.model.response.init.InitResponse;
import yterletskyi.com.vunglesdk.sdk.model.response.preload.Ad;
import yterletskyi.com.vunglesdk.sdk.model.response.preload.PreloadResponse;
import yterletskyi.com.vunglesdk.sdk.model.response.reportad.ReportAdResponse;
import yterletskyi.com.vunglesdk.sdk.model.response.willplayad.WillPlayAdResponse;
import yterletskyi.com.vunglesdk.sdk.utils.DownloadTask;
import yterletskyi.com.vunglesdk.sdk.utils.IndexHtmlChanger;
import yterletskyi.com.vunglesdk.sdk.utils.UnzipManager;
import yterletskyi.com.vunglesdk.sdk.vast.Tag;
import yterletskyi.com.vunglesdk.sdk.vast.VastBuilder;
import yterletskyi.com.vunglesdk.sdk.vast.VastCreator;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class Sdk {

    public static final String VERSION = "5.0.0";
    private static final String INIT_ENDPOINT = "https://ads.api.vungle.com/config";
    private static final String TAG = "VungleSdk";
    private static Sdk INSTANCE;
    private String mAppId;
    private Context mApplicationContext;
    private IApiService mApiService;
    private InitResponse mInitResponse;
    private Map<String, VideoAd> mAdMap;

    private Sdk(Context applicationContext) {
        mApplicationContext = applicationContext;
        mAdMap = new HashMap<>();
        createApiInterface();
    }

    public static Sdk getInstance(Context applicationContext) {
        if (INSTANCE == null) {
            INSTANCE = new Sdk(applicationContext);
        }
        return INSTANCE;
    }

    public boolean isInitialized() {
        return mInitResponse != null;
    }

    private void createApiInterface() {
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(INIT_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(IApiService.class);
    }

    public void initialize(String appId, List<String> placementList, final OnInitListener listener) {
        mAppId = appId;

        RequestBuilder requestBuilder = new RequestBuilder();
        GlobalRequest request = requestBuilder.buildInitSdkRequest(mApplicationContext, mAppId, placementList);

        Call<InitResponse> call = mApiService.initSDK(Sdk.VERSION, request);
        call.enqueue(new Callback<InitResponse>() {
            @Override
            public void onResponse(@NonNull Call<InitResponse> call, @NonNull retrofit2.Response<InitResponse> response) {
                InitResponse initResponse = response.body();
                if (initResponse != null) {
                    mInitResponse = initResponse;
                    listener.onInitSucceeded();
                } else {
                    listener.onInitFailed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<InitResponse> call, @NonNull Throwable t) {
                listener.onInitFailed();
            }
        });
    }

    private GlobalRequest buildPreloadRequestForAd(VideoAd videoAd) {
        RequestBuilder requestBuilder = new RequestBuilder();
        return requestBuilder.buildPreloadAdRequest(mApplicationContext, mAppId, videoAd.getPlacementId(), mInitResponse);
    }

    public void preloadAd(String placementId, OnAdListener onAdListener) {
        if (!isInitialized()) {
            return;
        }
        final VideoAd videoAd = new VideoAd(placementId);
        videoAd.setOnAdListener(onAdListener);
        mAdMap.put(placementId, videoAd);

        GlobalRequest preloadRequest = buildPreloadRequestForAd(videoAd);

        Call<PreloadResponse> responseCall = mApiService.preloadAd(mInitResponse.endpoints.ads, preloadRequest);
        responseCall.enqueue(new Callback<PreloadResponse>() {
            @Override
            public void onResponse(@NonNull Call<PreloadResponse> call, @NonNull retrofit2.Response<PreloadResponse> response) {
                try {
                    PreloadResponse body = response.body();
                    videoAd.setPreloadResponse(body);
                    downloadPostBundle(videoAd, body.ads.get(0).adMarkup.postBundle);
                    String vast = composeVastXml(body);
                    videoAd.setVastXml(vast);
                    videoAd.getOnAdListener().onAdLoaded();
                } catch (Exception e) {
                    videoAd.getOnAdListener().onAdFailedToLoad();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PreloadResponse> call, @NonNull Throwable t) {
                videoAd.getOnAdListener().onAdFailedToLoad();
            }
        });
    }

    private String composeVastXml(PreloadResponse result) {
        VastCreator vastCreator = new VastCreator();
        Tag tag = vastCreator.composeVastTagForAd(result.ads.get(0));
        return new VastBuilder().buildFromTag(tag);
    }

    private void downloadPostBundle(final VideoAd videoAd, String postBundleUrl) throws Exception {
        final String fileName = Uri.parse(postBundleUrl).getLastPathSegment();
        final File file = File.createTempFile(fileName, null, mApplicationContext.getCacheDir());
        DownloadTask downloadTask = new DownloadTask(postBundleUrl, file);
        downloadTask.setOnDownloadListener(new DownloadTask.OnDownloadListener() {
            @Override
            public void onDownloadCompleted(File downloadedFile) {
                File postBundleFile = new File(file.getParentFile().toString() + "/" + fileName.substring(0, fileName.indexOf('-')));
                videoAd.setPostrollBundleFile(postBundleFile);
                unzipFile(downloadedFile, postBundleFile);
                File htmlFile = findIndexHtmlFile(postBundleFile);
                injectAndroidInterfaceIntoIndexHtmlScript(htmlFile);
            }

            @Override
            public void onDownloadFailed() {
                videoAd.getOnAdListener().onAdFailedToLoad();
            }
        });
        downloadTask.execute();
    }

    private void injectAndroidInterfaceIntoIndexHtmlScript(File indexHtmlFile) {
        if (indexHtmlFile != null) {
            IndexHtmlChanger indexHtmlChanger = new IndexHtmlChanger();
            indexHtmlChanger.change(indexHtmlFile);
        }
    }

    private void unzipFile(File file, File destination) {
        UnzipManager unzipManager = new UnzipManager(file, destination);
        unzipManager.unzip();
    }

    public void playAd(final Activity activity, String placementId) {
        if (!isInitialized()) {
            return;
        }
        final VideoAd videoAd = mAdMap.get(placementId);
        if (videoAd == null || !videoAd.isLoaded()) {
            return;
        }
        final OnAdListener onAdListener = videoAd.getOnAdListener();

        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        final VASTPlayer vastPlayer = new VASTPlayer(activity);
        vastPlayer.setVASTPlayerListener(
                new VASTPlayer.VASTPlayerListener() {
                    @Override
                    public void vastReady() {
                        vastPlayer.play();
                        sendWillPlayAdRequest(videoAd);
                        onAdListener.onAdStarted();
                    }

                    @Override
                    public void vastError(int error) {
                        onAdListener.onAdFailedToLoad();
                    }

                    @Override
                    public void vastClick() {
                        Log.i(TAG, "vastClick: ");
                    }

                    @Override
                    public void vastComplete() {
                        onAdListener.onAdCompleted();
                        showPostRoll(activity, videoAd);
                    }

                    @Override
                    public void vastDismiss() {

                    }
                });
        String vastXmlString = videoAd.getVastXml();
        vastPlayer.loadVideoWithData(vastXmlString);
    }

    private void sendWillPlayAdRequest(VideoAd videoAd) {
        String url = mInitResponse.endpoints.willPlayAd;
        Ad ad = videoAd.getPreloadResponse().ads.get(0);
        Placement placement = getPlacementForAd(videoAd.getPlacementId());
        GlobalRequest request = new RequestBuilder().buildPlayingAdRequest(mApplicationContext, mAppId, ad.adMarkup.adToken, placement);
        Call<WillPlayAdResponse> call = mApiService.willPlayAd(url, request);
        call.enqueue(new NullRetrofitCallback<WillPlayAdResponse>());
    }

    private Placement getPlacementForAd(String adId) {
        Placement placement = new Placement();
        List<yterletskyi.com.vunglesdk.sdk.model.response.init.Placement> placements = mInitResponse.placements;
        placement.withReferenceId(adId);
        for (yterletskyi.com.vunglesdk.sdk.model.response.init.Placement itPlacement : placements) {
            if (itPlacement.referenceId.equals(adId)) {
                placement.withAutoCached(itPlacement.isAutoCached);
                break;
            }
        }
        return placement;
    }

    private File findIndexHtmlFile(File postBundleFile) {
        return new FileFinder().findFile(postBundleFile, "index.html");
    }

    private void showPostRoll(Activity activity, VideoAd videoAd) {
        File postBundleFile = videoAd.getPostrollBundleFile();
        File indexHtml = findIndexHtmlFile(postBundleFile);
        if (!activity.isFinishing() && indexHtml != null) {
            showWebViewDialog(activity, videoAd, indexHtml);
        }
    }

    private void showWebViewDialog(final Activity activity, final VideoAd videoAd, File indexHtml) {
        final WebViewDialog webViewDialog = new WebViewDialog(activity, android.R.style.Theme_NoTitleBar_Fullscreen);
        webViewDialog.setIndexHtmlFile(indexHtml);
        webViewDialog.setOnPostrollListener(new OnPostrollListener() {
            @Override
            public void onCloseClicked() {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                webViewDialog.dismiss();
                videoAd.getOnAdListener().onAdClosed();
                sendReportAdRequest(videoAd);
            }

            @Override
            public void onReplayClicked() {
                webViewDialog.dismiss();
                playAd(activity, videoAd.getPlacementId());
            }

            @Override
            public void onDownloadClicked() {
                String callToActionUrl = videoAd.getPreloadResponse().ads.get(0).adMarkup.callToActionUrl;
                new HyperlinkViewer().openViewIntent(mApplicationContext, callToActionUrl);
                sendPostrollClickEvents(videoAd);
            }
        });
        webViewDialog.show();
        sendPostrollViewEvents(videoAd);
    }

    private void sendReportAdRequest(VideoAd videoAd) {
        String placementId = videoAd.getPlacementId();
        Placement placement = getPlacementForAd(placementId);
        String token = videoAd.getPreloadResponse().ads.get(0).adMarkup.adToken;
        WillPlayAdRequest willPlayAdRequest = new WillPlayAdRequest(placement, token);
        String url = mInitResponse.endpoints.reportAd;
        Call<ReportAdResponse> call = mApiService.reportAd(url, willPlayAdRequest);
        call.enqueue(new NullRetrofitCallback<ReportAdResponse>());
    }

    private void sendPostrollViewEvents(VideoAd videoAd) {
        List<String> postrollViewUrls = videoAd.getPreloadResponse().ads.get(0).adMarkup.tpat.postrollView;
        for (String url : postrollViewUrls) {
            sendGetRequest(url);
        }
    }

    private void sendPostrollClickEvents(VideoAd videoAd) {
        List<String> postrollClickEvents = videoAd.getPreloadResponse().ads.get(0).adMarkup.tpat.postrollClick;
        for (String url : postrollClickEvents) {
            sendGetRequest(url);
        }
    }

    private void sendGetRequest(String url) {
        GetRequestSender getRequestSender = new GetRequestSender(mApiService);
        getRequestSender.send(url);
    }


}