package yterletskyi.com.vunglesdk.sdk.model.response.preload;


import com.google.gson.annotations.SerializedName;

public class Ad {

    @SerializedName("placement_reference_id")
    public String placementReferenceId;
    @SerializedName("ad_markup")
    public AdMarkup adMarkup;

}
