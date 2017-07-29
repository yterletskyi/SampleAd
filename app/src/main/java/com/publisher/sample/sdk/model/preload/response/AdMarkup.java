
package com.publisher.sample.sdk.model.preload.response;

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
    @SerializedName("callToActionDest")
    @Expose
    public String callToActionDest;
    @SerializedName("callToActionUrl")
    @Expose
    public String callToActionUrl;
    @SerializedName("adType")
    @Expose
    public String adType;
    @SerializedName("templateURL")
    @Expose
    public String templateURL;
    @SerializedName("templateSettings")
    @Expose
    public TemplateSettings templateSettings;
    @SerializedName("templateId")
    @Expose
    public String templateId;
    @SerializedName("template_type")
    @Expose
    public String templateType;
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

    public AdMarkup withId(String id) {
        this.id = id;
        return this;
    }

    public AdMarkup withCampaign(String campaign) {
        this.campaign = campaign;
        return this;
    }

    public AdMarkup withAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public AdMarkup withExpiry(int expiry) {
        this.expiry = expiry;
        return this;
    }

    public AdMarkup withTpat(Tpat tpat) {
        this.tpat = tpat;
        return this;
    }

    public AdMarkup withDelay(int delay) {
        this.delay = delay;
        return this;
    }

    public AdMarkup withShowClose(int showClose) {
        this.showClose = showClose;
        return this;
    }

    public AdMarkup withShowCloseIncentivized(int showCloseIncentivized) {
        this.showCloseIncentivized = showCloseIncentivized;
        return this;
    }

    public AdMarkup withCountdown(int countdown) {
        this.countdown = countdown;
        return this;
    }

    public AdMarkup withUrl(String url) {
        this.url = url;
        return this;
    }

    public AdMarkup withVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
        return this;
    }

    public AdMarkup withVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
        return this;
    }

    public AdMarkup withCallToActionDest(String callToActionDest) {
        this.callToActionDest = callToActionDest;
        return this;
    }

    public AdMarkup withCallToActionUrl(String callToActionUrl) {
        this.callToActionUrl = callToActionUrl;
        return this;
    }

    public AdMarkup withAdType(String adType) {
        this.adType = adType;
        return this;
    }

    public AdMarkup withTemplateURL(String templateURL) {
        this.templateURL = templateURL;
        return this;
    }

    public AdMarkup withTemplateSettings(TemplateSettings templateSettings) {
        this.templateSettings = templateSettings;
        return this;
    }

    public AdMarkup withTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public AdMarkup withTemplateType(String templateType) {
        this.templateType = templateType;
        return this;
    }

    public AdMarkup withChk(String chk) {
        this.chk = chk;
        return this;
    }

    public AdMarkup withRetryCount(int retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public AdMarkup withAsyncThreshold(int asyncThreshold) {
        this.asyncThreshold = asyncThreshold;
        return this;
    }

    public AdMarkup withAdToken(String adToken) {
        this.adToken = adToken;
        return this;
    }

    public AdMarkup withVideoObjectId(String videoObjectId) {
        this.videoObjectId = videoObjectId;
        return this;
    }

}
