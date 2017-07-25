
package com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.play.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Endpoints {

    @SerializedName("new")
    @Expose
    public String _new;
    @SerializedName("ads")
    @Expose
    public String ads;
    @SerializedName("will_play_ad")
    @Expose
    public String willPlayAd;
    @SerializedName("report_ad")
    @Expose
    public String reportAd;

}
