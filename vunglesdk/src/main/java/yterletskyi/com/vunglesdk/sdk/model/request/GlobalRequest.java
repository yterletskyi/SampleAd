package yterletskyi.com.vunglesdk.sdk.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalRequest {

    @SerializedName("app")
    public App app;
    @SerializedName("device")
    public Device device;
    @SerializedName("request")
    public Request request;

    public GlobalRequest withApp(App app) {
        this.app = app;
        return this;
    }

    public GlobalRequest withDevice(Device device) {
        this.device = device;
        return this;
    }

    public GlobalRequest withRequest(Request request) {
        this.request = request;
        return this;
    }

}
