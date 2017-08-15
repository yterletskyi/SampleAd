package yterletskyi.com.vunglesdk.sdk.model.init.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InitResponse {

    @SerializedName("endpoints")
    public Endpoints endpoints;
    @SerializedName("placements")
    public List<Placement> placements;
    @SerializedName("config")
    public Config config;
    @SerializedName("will_play_ad")
    public WillPlayAd willPlayAd;
    @SerializedName("playback")
    public Playback playback;
    @SerializedName("viewability")
    public Viewability viewability;
    @SerializedName("exception_reporting")
    public boolean exceptionReporting;
    @SerializedName("vduid")
    public String vduid;

}
