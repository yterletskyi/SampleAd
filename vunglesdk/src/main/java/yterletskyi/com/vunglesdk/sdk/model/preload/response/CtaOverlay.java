
package yterletskyi.com.vunglesdk.sdk.model.preload.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CtaOverlay {

    @SerializedName("enabled")
    @Expose
    public boolean enabled;
    @SerializedName("show_onclick")
    @Expose
    public boolean showOnclick;
    @SerializedName("time_enabled")
    @Expose
    public int timeEnabled;
    @SerializedName("time_show")
    @Expose
    public int timeShow;
    @SerializedName("click_area")
    @Expose
    public int clickArea;

}
