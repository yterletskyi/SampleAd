package yterletskyi.com.vunglesdk.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tpat {

    @SerializedName("moat")
    @Expose
    public Moat moat;
    @SerializedName("play_percentage")
    @Expose
    public List<PlayPercentage> playPercentage = null;
    @SerializedName("mute")
    @Expose
    public List<String> mute = null;
    @SerializedName("unmute")
    @Expose
    public List<String> unmute = null;
    @SerializedName("video_close")
    @Expose
    public List<String> videoClose = null;
    @SerializedName("postroll_click")
    @Expose
    public List<String> postrollClick = null;
    @SerializedName("postroll_view")
    @Expose
    public List<String> postrollView = null;

}
