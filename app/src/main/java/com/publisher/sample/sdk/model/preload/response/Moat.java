
package com.publisher.sample.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Moat {

    @SerializedName("is_enabled")
    @Expose
    public boolean isEnabled;
    @SerializedName("extra_vast")
    @Expose
    public String extraVast;

    public Moat withIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public Moat withExtraVast(String extraVast) {
        this.extraVast = extraVast;
        return this;
    }

}
