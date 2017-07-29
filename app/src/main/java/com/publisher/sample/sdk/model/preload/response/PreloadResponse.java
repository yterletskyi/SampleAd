
package com.publisher.sample.sdk.model.preload.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreloadResponse {

    @SerializedName("ads")
    @Expose
    public List<Ad> ads = null;

    public PreloadResponse withAds(List<Ad> ads) {
        this.ads = ads;
        return this;
    }

}
