package yterletskyi.com.vunglesdk.sdk.model.preload.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayPercentage {

    @SerializedName("checkpoint")
    public double checkpoint;
    @SerializedName("urls")
    public List<String> urls = null;

}
