package com.publisher.sample.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalRequest {

    @SerializedName("app")
    @Expose
    public App app;
    @SerializedName("device")
    @Expose
    public Device device;
    @SerializedName("request")
    @Expose
    public Request request;

    public GlobalRequest withApp(App app) {
        this.app = app;
        return this;
    }

    public GlobalRequest withDevice(Device device) {
        this.device = device;
        return this;
    }

    public GlobalRequest withRequest(Request request) {
        this.request = request;
        return this;
    }

}
