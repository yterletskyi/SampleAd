
package com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.init.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Placement {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("reference_id")
    @Expose
    public String referenceId;
    @SerializedName("is_auto_cached")
    @Expose
    public boolean isAutoCached;
    @SerializedName("is_incentivized")
    @Expose
    public boolean isIncentivized;

}
