package com.publisher.sample.sdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Request {

    @SerializedName("placements")
    @Expose
    public List<String> placements = null;

    public Request withPlacements(List<String> placements) {
        this.placements = placements;
        return this;
    }

}
