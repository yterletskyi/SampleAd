package yterletskyi.com.vunglesdk.sdk;

import java.io.File;

import yterletskyi.com.vunglesdk.sdk.model.preload.response.PreloadResponse;

/**
 * Created by yterletskyi on 15.08.17.
 */

class VideoAd {

    private final String mPlacementId;
    private String mVastXml;
    private File mPostrollBundleFile;
    private OnAdListener mOnAdListener;
    private PreloadResponse mPreloadResponse;

    public VideoAd(String placementId) {
        mPlacementId = placementId;
    }

    public String getVastXml() {
        return mVastXml;
    }

    public void setVastXml(String vastXml) {
        mVastXml = vastXml;
    }

    public File getPostrollBundleFile() {
        return mPostrollBundleFile;
    }

    public void setPostrollBundleFile(File postrollBundleFile) {
        mPostrollBundleFile = postrollBundleFile;
    }

    public PreloadResponse getPreloadResponse() {
        return mPreloadResponse;
    }

    public void setPreloadResponse(PreloadResponse preloadResponse) {
        mPreloadResponse = preloadResponse;
    }

    public OnAdListener getOnAdListener() {
        return mOnAdListener;
    }

    public void setOnAdListener(OnAdListener onAdListener) {
        mOnAdListener = onAdListener;
    }

    public String getPlacementId() {
        return mPlacementId;
    }

    public boolean isLoaded() {
        return mVastXml != null;
    }
}