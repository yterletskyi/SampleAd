package yterletskyi.com.vunglesdk.sdk.model.response.preload;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tpat {

    @SerializedName("moat")
    public Moat moat;
    @SerializedName("play_percentage")
    public List<PlayPercentage> playPercentage = null;
    @SerializedName("mute")
    public List<String> mute = null;
    @SerializedName("unmute")
    public List<String> unmute = null;
    @SerializedName("video_close")
    public List<String> videoClose = null;
    @SerializedName("postroll_click")
    public List<String> postrollClick = null;
    @SerializedName("postroll_view")
    public List<String> postrollView = null;

}
