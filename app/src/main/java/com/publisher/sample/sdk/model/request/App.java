package com.publisher.sample.sdk.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class App {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("bundle")
    @Expose
    public String bundle;
    @SerializedName("ver")
    @Expose
    public String ver;

    public App withId(String id) {
        this.id = id;
        return this;
    }

    public App withBundle(String bundle) {
        this.bundle = bundle;
        return this;
    }

    public App withVer(String ver) {
        this.ver = ver;
        return this;
    }

}
