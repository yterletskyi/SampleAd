
package com.publisher.sample.sdk.model.init.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playback {

    @SerializedName("buffer_timeout")
    @Expose
    public int bufferTimeout;

}
