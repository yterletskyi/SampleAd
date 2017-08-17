package yterletskyi.com.vunglesdk.sdk.model.request.global;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreloadAdRequest implements IRequest {

    @SerializedName("placements")
    public List<String> placements;

    public PreloadAdRequest withPlacements(List<String> placements) {
        this.placements = placements;
        return this;
    }

}
