
package yterletskyi.com.vunglesdk.sdk.model.preload.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayPercentage {

    @SerializedName("checkpoint")
    @Expose
    public double checkpoint;
    @SerializedName("urls")
    @Expose
    public List<String> urls = null;

}
