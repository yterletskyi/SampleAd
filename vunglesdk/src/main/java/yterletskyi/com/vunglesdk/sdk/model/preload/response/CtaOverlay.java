package yterletskyi.com.vunglesdk.sdk.model.preload.response;


import com.google.gson.annotations.SerializedName;

public class CtaOverlay {

    @SerializedName("enabled")
    public boolean enabled;
    @SerializedName("show_onclick")
    public boolean showOnclick;
    @SerializedName("time_enabled")
    public int timeEnabled;
    @SerializedName("time_show")
    public int timeShow;
    @SerializedName("click_area")
    public int clickArea;

}
