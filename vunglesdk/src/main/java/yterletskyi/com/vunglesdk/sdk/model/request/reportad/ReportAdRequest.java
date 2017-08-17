package yterletskyi.com.vunglesdk.sdk.model.request.reportad;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

import yterletskyi.com.vunglesdk.sdk.model.request.global.IRequest;

public class ReportAdRequest implements IRequest {

    @SerializedName("ttDownload")
    public int ttDownload;
    @SerializedName("adStartTime")
    public long adStartTime;
    @SerializedName("app_id")
    public String appId;
    @SerializedName("campaign")
    public String campaign;
    @SerializedName("adDuration")
    public long adDuration;
    @SerializedName("incentivized")
    public int incentivized;
    @SerializedName("placement_reference_id")
    public String placementReferenceId;
    @SerializedName("ad_token")
    public String adToken;
    @SerializedName("url")
    public String url;
    @SerializedName("plays")
    public List<Play> plays = null;
    @SerializedName("clickedThrough")
    public List<Object> clickedThrough = null;
    @SerializedName("errors")
    public List<Object> errors = null;
    @SerializedName("adType")
    public String adType;

    public ReportAdRequest withTtDownload(int ttDownload) {
        this.ttDownload = ttDownload;
        return this;
    }

    public ReportAdRequest withAdStartTime(long adStartTime) {
        this.adStartTime = adStartTime;
        return this;
    }

    public ReportAdRequest withAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public ReportAdRequest withCampaign(String campaign) {
        this.campaign = campaign;
        return this;
    }

    public ReportAdRequest withAdDuration(long adDuration) {
        this.adDuration = adDuration;
        return this;
    }

    public ReportAdRequest withIncentivized(int incentivized) {
        this.incentivized = incentivized;
        return this;
    }

    public ReportAdRequest withPlacementReferenceId(String placementReferenceId) {
        this.placementReferenceId = placementReferenceId;
        return this;
    }

    public ReportAdRequest withAdToken(String adToken) {
        this.adToken = adToken;
        return this;
    }

    public ReportAdRequest withUrl(String url) {
        this.url = url;
        return this;
    }

    public ReportAdRequest withPlays(Play... plays) {
        this.plays = Arrays.asList(plays);
        return this;
    }

    public ReportAdRequest withClickedThrough(List<Object> clickedThrough) {
        this.clickedThrough = clickedThrough;
        return this;
    }

    public ReportAdRequest withErrors(List<Object> errors) {
        this.errors = errors;
        return this;
    }

    public ReportAdRequest withAdType(String adType) {
        this.adType = adType;
        return this;
    }

}
