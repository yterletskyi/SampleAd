package yterletskyi.com.vunglesdk.sdk.model.request.willplayad;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yterletskyi on 16.08.17.
 */

public class Placement {

    @SerializedName("reference_id")
    public String referenceId;

    @SerializedName("is_auto_cached")
    public boolean isAutoCached;

    public Placement withReferenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public Placement withAutoCached(boolean isAutoCached) {
        this.isAutoCached = isAutoCached;
        return this;
    }

}
