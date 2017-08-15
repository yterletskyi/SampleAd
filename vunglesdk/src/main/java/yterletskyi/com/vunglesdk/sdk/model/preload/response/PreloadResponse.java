package yterletskyi.com.vunglesdk.sdk.model.preload.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yterletskyi on 25.07.17.
 */

public class PreloadResponse {

    @SerializedName("ads")
    public List<Ad> ads;

}
