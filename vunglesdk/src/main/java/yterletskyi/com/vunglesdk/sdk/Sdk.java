package yterletskyi.com.vunglesdk.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import org.nexage.sourcekit.vast.VASTPlayer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yterletskyi.com.vunglesdk.sdk.api.IApiService;
import yterletskyi.com.vunglesdk.sdk.model.init.response.InitResponse;
import yterletskyi.com.vunglesdk.sdk.model.preload.response.PreloadResponse;
import yterletskyi.com.vunglesdk.sdk.model.request.GlobalRequest;
import yterletskyi.com.vunglesdk.sdk.model.request.RequestBuilder;
import yterletskyi.com.vunglesdk.sdk.utils.DownloadTask;
import yterletskyi.com.vunglesdk.sdk.utils.IndexHtmlChanger;
import yterletskyi.com.vunglesdk.sdk.utils.UnzipManager;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class Sdk {

    public static final String APP_VERSION = "1.0";
    private static final String VERSION = "5.0.0";
    private static final String TAG = "VungleSdk";
    private static final String API_ENDPOINT = "https://api.vungle.com";
    private static Sdk INSTANCE;
    private String mAppId;
    private VASTPlayer mVASTPlayer;
    private IApiService mApiService;
    private InitResponse mInitResponse;
    private Context mApplicationContext;
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
                .baseUrl(API_ENDPOINT)
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
        GlobalRequest globalRequest = new GlobalRequest();
        if (mInitResponse != null) {
            globalRequest = requestBuilder.buildPreloadAdRequest(mApplicationContext, mAppId, videoAd.getPlacementId(), mInitResponse);
        }
        return globalRequest;
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
                    String vast = formVastXml(body);
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

    private String formVastXml(PreloadResponse result) {
        VastCreator vastCreator = new VastCreator();
        return vastCreator.build(result);
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

        mVASTPlayer = new VASTPlayer(activity, new VASTPlayer.VASTPlayerListener() {
            @Override
            public void vastReady() {
                mVASTPlayer.play();
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
        mVASTPlayer.loadVideoWithData(vastXmlString);
    }

    private File findIndexHtmlFile(File postBundleFile) {
        File[] indexHtmls = postBundleFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.equals("index.html");
            }
        });
        File file;
        if (indexHtmls.length > 0) {
            file = indexHtmls[0];
        } else {
            file = null;
        }
        return file;
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
            }

            @Override
            public void onReplayClicked() {
                webViewDialog.dismiss();
                playAd(activity, videoAd.getPlacementId());
            }

            @Override
            public void onDownloadClicked() {
                openBrowseIntent(videoAd.getPreloadResponse().ads.get(0).adMarkup.callToActionUrl);
            }
        });
        webViewDialog.show();
    }

    private void openBrowseIntent(String url) {
        Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        browseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mApplicationContext.startActivity(browseIntent);
    }
}