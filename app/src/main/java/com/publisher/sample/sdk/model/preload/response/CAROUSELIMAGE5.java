
package com.publisher.sample.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CAROUSELIMAGE5 {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("extension")
    @Expose
    public String extension;

    public CAROUSELIMAGE5 withUrl(String url) {
        this.url = url;
        return this;
    }

    public CAROUSELIMAGE5 withExtension(String extension) {
        this.extension = extension;
        return this;
    }

}
