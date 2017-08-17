package yterletskyi.com.vunglesdk.sdk.model.request.willplayad;

import com.google.gson.annotations.SerializedName;

import yterletskyi.com.vunglesdk.sdk.model.request.global.IRequest;

/**
 * Created by yterletskyi on 16.08.17.
 */

public class WillPlayAdRequest implements IRequest {

    @SerializedName("placement")
    public Placement placement;

    @SerializedName("ad_token")
    public String adToken;

    public WillPlayAdRequest() {
    }

    public WillPlayAdRequest(Placement placement, String adToken) {
        this.placement = placement;
        this.adToken = adToken;
    }

    public WillPlayAdRequest withPlacement(Placement placement) {
        this.placement = placement;
        return this;
    }

    public WillPlayAdRequest withAdToken(String adToken) {
        this.adToken = adToken;
        return this;
    }
}
