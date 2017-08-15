package yterletskyi.com.vunglesdk.sdk.model.preload.response;


import com.google.gson.annotations.SerializedName;

public class Ad {

    @SerializedName("placement_reference_id")
    public String placementReferenceId;
    @SerializedName("ad_markup")
    public AdMarkup adMarkup;

}
