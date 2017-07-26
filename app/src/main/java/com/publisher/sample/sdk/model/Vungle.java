package com.publisher.sample.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.publisher.sample.sdk.model.preload.requestad.Android;

public class Vungle {

    @SerializedName("android")
    @Expose
    public Android android;
    @SerializedName("platform")
    @Expose
    public String platform;

    public Vungle withAndroid(Android android) {
        this.android = android;
        return this;
    }

    public Vungle withPlatform(String platform) {
        this.platform = platform;
        return this;
    }

}
