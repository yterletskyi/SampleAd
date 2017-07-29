
package com.publisher.sample.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CAROUSELIMAGE4 {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("extension")
    @Expose
    public String extension;

    public CAROUSELIMAGE4 withUrl(String url) {
        this.url = url;
        return this;
    }

    public CAROUSELIMAGE4 withExtension(String extension) {
        this.extension = extension;
        return this;
    }

}
