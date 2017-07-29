
package com.publisher.sample.sdk.model.preload.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tpat {

    @SerializedName("moat")
    @Expose
    public Moat moat;
    @SerializedName("clickUrl")
    @Expose
    public List<String> clickUrl = null;
    @SerializedName("checkpoint.0")
    @Expose
    public List<String> checkpoint0 = null;
    @SerializedName("checkpoint.25")
    @Expose
    public List<String> checkpoint25 = null;
    @SerializedName("checkpoint.50")
    @Expose
    public List<String> checkpoint50 = null;
    @SerializedName("checkpoint.75")
    @Expose
    public List<String> checkpoint75 = null;
    @SerializedName("checkpoint.100")
    @Expose
    public List<String> checkpoint100 = null;
    @SerializedName("postroll.view")
    @Expose
    public List<String> postrollView = null;
    @SerializedName("postroll.click")
    @Expose
    public List<String> postrollClick = null;
    @SerializedName("video.close")
    @Expose
    public List<String> videoClose = null;
    @SerializedName("video.unmute")
    @Expose
    public List<String> videoUnmute = null;
    @SerializedName("video.mute")
    @Expose
    public List<String> videoMute = null;

    public Tpat withMoat(Moat moat) {
        this.moat = moat;
        return this;
    }

    public Tpat withClickUrl(List<String> clickUrl) {
        this.clickUrl = clickUrl;
        return this;
    }

    public Tpat withCheckpoint0(List<String> checkpoint0) {
        this.checkpoint0 = checkpoint0;
        return this;
    }

    public Tpat withCheckpoint25(List<String> checkpoint25) {
        this.checkpoint25 = checkpoint25;
        return this;
    }

    public Tpat withCheckpoint50(List<String> checkpoint50) {
        this.checkpoint50 = checkpoint50;
        return this;
    }

    public Tpat withCheckpoint75(List<String> checkpoint75) {
        this.checkpoint75 = checkpoint75;
        return this;
    }

    public Tpat withCheckpoint100(List<String> checkpoint100) {
        this.checkpoint100 = checkpoint100;
        return this;
    }

    public Tpat withPostrollView(List<String> postrollView) {
        this.postrollView = postrollView;
        return this;
    }

    public Tpat withPostrollClick(List<String> postrollClick) {
        this.postrollClick = postrollClick;
        return this;
    }

    public Tpat withVideoClose(List<String> videoClose) {
        this.videoClose = videoClose;
        return this;
    }

    public Tpat withVideoUnmute(List<String> videoUnmute) {
        this.videoUnmute = videoUnmute;
        return this;
    }

    public Tpat withVideoMute(List<String> videoMute) {
        this.videoMute = videoMute;
        return this;
    }

}
