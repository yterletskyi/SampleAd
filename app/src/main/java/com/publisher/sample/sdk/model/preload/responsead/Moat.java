
package com.publisher.sample.sdk.model.preload.responsead;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Moat {

    @SerializedName("is_enabled")
    @Expose
    public boolean isEnabled;
    @SerializedName("extra_vast")
    @Expose
    public String extraVast;

}
