package yterletskyi.com.vunglesdk.sdk.model.preload.response;


import com.google.gson.annotations.SerializedName;

public class AdMarkup {

    @SerializedName("id")
    public String id;
    @SerializedName("campaign")
    public String campaign;
    @SerializedName("app_id")
    public String appId;
    @SerializedName("expiry")
    public int expiry;
    @SerializedName("tpat")
    public Tpat tpat;
    @SerializedName("delay")
    public int delay;
    @SerializedName("showClose")
    public int showClose;
    @SerializedName("showCloseIncentivized")
    public int showCloseIncentivized;
    @SerializedName("countdown")
    public int countdown;
    @SerializedName("url")
    public String url;
    @SerializedName("videoWidth")
    public int videoWidth;
    @SerializedName("videoHeight")
    public int videoHeight;
    @SerializedName("md5")
    public String md5;
    @SerializedName("postBundle")
    public String postBundle;
    @SerializedName("cta_overlay")
    public CtaOverlay ctaOverlay;
    @SerializedName("callToActionDest")
    public String callToActionDest;
    @SerializedName("callToActionUrl")
    public String callToActionUrl;
    @SerializedName("adType")
    public String adType;
    @SerializedName("chk")
    public String chk;
    @SerializedName("retryCount")
    public int retryCount;
    @SerializedName("asyncThreshold")
    public int asyncThreshold;
    @SerializedName("ad_token")
    public String adToken;
    @SerializedName("video_object_id")
    public String videoObjectId;

}
