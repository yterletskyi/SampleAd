
package com.publisher.sample.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class POWEREDBYVUNGLE {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("extension")
    @Expose
    public String extension;

    public POWEREDBYVUNGLE withUrl(String url) {
        this.url = url;
        return this;
    }

    public POWEREDBYVUNGLE withExtension(String extension) {
        this.extension = extension;
        return this;
    }

}
