
package com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.init.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WillPlayAd {

    @SerializedName("enabled")
    @Expose
    public boolean enabled;
    @SerializedName("request_timeout")
    @Expose
    public int requestTimeout;

}
