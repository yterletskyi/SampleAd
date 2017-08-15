package yterletskyi.com.vunglesdk.sdk;

import java.io.File;

import yterletskyi.com.vunglesdk.sdk.model.preload.response.PreloadResponse;

/**
 * Created by yterletskyi on 15.08.17.
 */

// TODO: 15.08.17 make it inaccessible from out of package
public class VideoAd {

    private final String mPlacementId;
    private String mVastXml;
    private File mPostBundleFile;
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

    public File getPostBundleFile() {
        return mPostBundleFile;
    }

    public void setPostBundleFile(File postBundleFile) {
        mPostBundleFile = postBundleFile;
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

}


