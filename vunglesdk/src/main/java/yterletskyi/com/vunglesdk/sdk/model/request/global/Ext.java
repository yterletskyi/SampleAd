package yterletskyi.com.vunglesdk.sdk.model.request.global;

import com.google.gson.annotations.SerializedName;

public class Ext {

    @SerializedName("vungle")
    public Vungle vungle;

    public Ext withVungle(Vungle vungle) {
        this.vungle = vungle;
        return this;
    }

}
