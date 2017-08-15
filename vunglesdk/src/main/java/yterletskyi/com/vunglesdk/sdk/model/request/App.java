package yterletskyi.com.vunglesdk.sdk.model.request;

import com.google.gson.annotations.SerializedName;

public class App {

    @SerializedName("id")
    public String id;
    @SerializedName("bundle")
    public String bundle;
    @SerializedName("ver")
    public String ver;

    public App withId(String id) {
        this.id = id;
        return this;
    }

    public App withBundle(String bundle) {
        this.bundle = bundle;
        return this;
    }

    public App withVer(String ver) {
        this.ver = ver;
        return this;
    }

}
