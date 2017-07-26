
package com.publisher.sample.sdk.model.preload.responsead;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayPercentage {

    @SerializedName("checkpoint")
    @Expose
    public double checkpoint;
    @SerializedName("urls")
    @Expose
    public List<String> urls = null;

}
