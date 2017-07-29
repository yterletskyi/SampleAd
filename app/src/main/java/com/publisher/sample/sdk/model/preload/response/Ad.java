
package com.publisher.sample.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ad {

    @SerializedName("placement_reference_id")
    @Expose
    public String placementReferenceId;
    @SerializedName("ad_markup")
    @Expose
    public AdMarkup adMarkup;

    public Ad withPlacementReferenceId(String placementReferenceId) {
        this.placementReferenceId = placementReferenceId;
        return this;
    }

    public Ad withAdMarkup(AdMarkup adMarkup) {
        this.adMarkup = adMarkup;
        return this;
    }

}
