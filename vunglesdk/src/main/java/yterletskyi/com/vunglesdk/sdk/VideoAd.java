package yterletskyi.com.vunglesdk.sdk;

import java.io.File;

import yterletskyi.com.vunglesdk.sdk.model.response.preload.Ad;

/**
 * Created by yterletskyi on 15.08.17.
 */

class VideoAd {

    private final String mPlacementId;
    private String mVastXml;
    private File mPostrollBundleFile;
    private OnAdListener mOnAdListener;
    private Ad mAdModel;
    private long mVideoLengthMiliis;
    private long mAdViewMiliis;

    public VideoAd(String placementId) {
        mPlacementId = placementId;
    }

    public long getVideoLengthMiliis() {
        return mVideoLengthMiliis;
    }

    public void setVideoLengthMiliis(long videoLengthMiliis) {

        mVideoLengthMiliis = videoLengthMiliis;
    }

    public long getAdViewMiliis() {
        return mAdViewMiliis;
    }

    public void setAdViewMiliis(long adViewMiliis) {
        mAdViewMiliis = adViewMiliis;
    }

    public Ad getAdModel() {
        return mAdModel;
    }

    public void setAdModel(Ad adModel) {
        mAdModel = adModel;
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