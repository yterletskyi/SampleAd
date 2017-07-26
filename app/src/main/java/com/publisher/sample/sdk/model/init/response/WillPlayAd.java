
package com.publisher.sample.sdk.model.init.response;

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
