package com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.responsead;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ad {

    @SerializedName("placement_reference_id")
    @Expose
    public String placementReferenceId;
    @SerializedName("ad_markup")
    @Expose
    public AdMarkup adMarkup;

}
