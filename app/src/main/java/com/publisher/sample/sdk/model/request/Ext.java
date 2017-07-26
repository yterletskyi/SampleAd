package com.publisher.sample.sdk.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ext {

    @SerializedName("vungle")
    @Expose
    public Vungle vungle;

    public Ext withVungle(Vungle vungle) {
        this.vungle = vungle;
        return this;
    }

}
