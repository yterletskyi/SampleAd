package yterletskyi.com.vunglesdk.sdk.model.request.global;

import com.google.gson.annotations.SerializedName;

public class Vungle {

    @SerializedName("android")
    public Android android;
    @SerializedName("platform")
    public String platform;

    public Vungle withAndroid(Android android) {
        this.android = android;
        return this;
    }

    public Vungle withPlatform(String platform) {
        this.platform = platform;
        return this;
    }

}
