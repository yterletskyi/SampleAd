package yterletskyi.com.vunglesdk.sdk.model.request.preloadad;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import yterletskyi.com.vunglesdk.sdk.model.request.global.IRequest;

public class PreloadAdRequest implements IRequest {

    @SerializedName("placements")
    public List<String> placements;

    public PreloadAdRequest withPlacements(List<String> placements) {
        this.placements = placements;
        return this;
    }

}
