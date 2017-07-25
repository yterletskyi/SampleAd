
package com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.responsead;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdMarkup {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("campaign")
    @Expose
    public String campaign;
    @SerializedName("app_id")
    @Expose
    public String appId;
    @SerializedName("expiry")
    @Expose
    public int expiry;
    @SerializedName("tpat")
    @Expose
    public Tpat tpat;
    @SerializedName("delay")
    @Expose
    public int delay;
    @SerializedName("showClose")
    @Expose
    public int showClose;
    @SerializedName("showCloseIncentivized")
    @Expose
    public int showCloseIncentivized;
    @SerializedName("countdown")
    @Expose
    public int countdown;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("videoWidth")
    @Expose
    public int videoWidth;
    @SerializedName("videoHeight")
    @Expose
    public int videoHeight;
    @SerializedName("md5")
    @Expose
    public String md5;
    @SerializedName("postBundle")
    @Expose
    public String postBundle;
    @SerializedName("cta_overlay")
    @Expose
    public CtaOverlay ctaOverlay;
    @SerializedName("callToActionDest")
    @Expose
    public String callToActionDest;
    @SerializedName("callToActionUrl")
    @Expose
    public String callToActionUrl;
    @SerializedName("adType")
    @Expose
    public String adType;
    @SerializedName("chk")
    @Expose
    public String chk;
    @SerializedName("retryCount")
    @Expose
    public int retryCount;
    @SerializedName("asyncThreshold")
    @Expose
    public int asyncThreshold;
    @SerializedName("ad_token")
    @Expose
    public String adToken;
    @SerializedName("video_object_id")
    @Expose
    public String videoObjectId;

}
