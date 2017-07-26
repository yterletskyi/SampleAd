
package com.publisher.sample.sdk.model.init.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitResponse {

    @SerializedName("endpoints")
    @Expose
    public Endpoints endpoints;
    @SerializedName("placements")
    @Expose
    public List<Placement> placements = null;
    @SerializedName("config")
    @Expose
    public Config config;
    @SerializedName("will_play_ad")
    @Expose
    public WillPlayAd willPlayAd;
    @SerializedName("playback")
    @Expose
    public Playback playback;
    @SerializedName("viewability")
    @Expose
    public Viewability viewability;
    @SerializedName("exception_reporting")
    @Expose
    public boolean exceptionReporting;
    @SerializedName("vduid")
    @Expose
    public String vduid;

}
