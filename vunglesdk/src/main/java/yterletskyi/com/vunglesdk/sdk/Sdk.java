package yterletskyi.com.vunglesdk.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import org.nexage.sourcekit.vast.VASTPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
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
    private File mPostBundleFile;

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
                downloadPostBundle(result.ads.get(0).adMarkup.postBundle);
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

    private void downloadPostBundle(String postBundleUrl) {
        final String fileName = Uri.parse(postBundleUrl).getLastPathSegment();
        Log.i(TAG, "downloadPostBundle: " + fileName);
        final File file;
        try {
            file = File.createTempFile(fileName, null, mContext.getCacheDir());
            DownloadTask downloadTask = new DownloadTask(postBundleUrl, file);
            downloadTask.setOnDownloadListener(new DownloadTask.OnDownloadListener() {
                @Override
                public void onDownloadCompleted(File downloadedFile) {
                    mPostBundleFile = new File(file.getParentFile().toString() + "/" + fileName.substring(0, fileName.indexOf('-')));
                    unzipFile(downloadedFile, mPostBundleFile);
                    appendFunctionToIndexHtml();
                }

                @Override
                public void onDownloadFailed() {
                    Log.i(TAG, "onDownloadFailed: ");
                }
            });
            downloadTask.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendFunctionToIndexHtml() {
        File html = findIndexHtmlFile();

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(html, "rw");
            long aPositionWhereIWantToGo = 99;
            randomAccessFile.seek(aPositionWhereIWantToGo);
            randomAccessFile.write("Im in teh fil, writn bites".getBytes());
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void unzipFile(File file, File destination) {
        Log.i(TAG, "unzipFile: ");
        UnzipManager unzipManager = new UnzipManager(file, destination);
        unzipManager.unzip();
        Log.i(TAG, "unzipFile: finished");
    }

    public void playAd() {
        showPostAdCompanion();

//        mVASTPlayer = new VASTPlayer(mContext, new VASTPlayer.VASTPlayerListener() {
//            @Override
//            public void vastReady() {
//                mVASTPlayer.play();
//                mOnAdListener.onAdStarted();
//            }
//
//            @Override
//            public void vastError(int error) {
//                mOnAdListener.onAdFailedToLoad();
//            }
//
//            @Override
//            public void vastClick() {
//                Log.i(TAG, "vastClick: ");
//            }
//
//            @Override
//            public void vastComplete() {
//                mOnAdListener.onAdCompleted();
//                showPostAdCompanion();
//            }
//
//            @Override
//            public void vastDismiss() {
//            }
//        });
//        mVASTPlayer.loadVideoWithData(mVastXml);
    }

    private File findIndexHtmlFile() {
        File[] indexHtmls = mPostBundleFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.equals("index.html");
            }
        });
        return indexHtmls[0];
    }

    private void showPostAdCompanion() {
        File indexHtml = findIndexHtmlFile();
        showWebViewActivity(indexHtml);
    }

    private void showWebViewActivity(File indexHtml) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("html", indexHtml);
        mContext.startActivity(intent);
    }

}
