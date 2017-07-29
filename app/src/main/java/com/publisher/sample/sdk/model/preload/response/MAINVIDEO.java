
package com.publisher.sample.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MAINVIDEO {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("extension")
    @Expose
    public String extension;

    public MAINVIDEO withUrl(String url) {
        this.url = url;
        return this;
    }

    public MAINVIDEO withExtension(String extension) {
        this.extension = extension;
        return this;
    }

}
