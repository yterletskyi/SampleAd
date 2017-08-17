package yterletskyi.com.vunglesdk.sdk.model.response._new;

/**
 * Created by yterletskyi on 17.08.17.
 */

import com.google.gson.annotations.SerializedName;

public class NewLaunchResponse {

    @SerializedName("isu")
    public String isu;
    @SerializedName("app_id")
    public String appId;
    @SerializedName("isIFA")
    public boolean isIFA;
    @SerializedName("ts")
    public String ts;
    @SerializedName("ip")
    public String ip;

}
