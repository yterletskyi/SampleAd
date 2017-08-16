package yterletskyi.com.vunglesdk.sdk.model.response.init;

import com.google.gson.annotations.SerializedName;

public class Placement {

    @SerializedName("id")
    public String id;
    @SerializedName("reference_id")
    public String referenceId;
    @SerializedName("is_auto_cached")
    public boolean isAutoCached;
    @SerializedName("is_incentivized")
    public boolean isIncentivized;

}
