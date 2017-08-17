package yterletskyi.com.vunglesdk.sdk.model.request.global;

import com.google.gson.annotations.SerializedName;

public class GlobalRequest {

    @SerializedName("app")
    public App app;

    @SerializedName("device")
    public Device device;

    @SerializedName("request")
    public IRequest request;

    public GlobalRequest withApp(App app) {
        this.app = app;
        return this;
    }

    public GlobalRequest withDevice(Device device) {
        this.device = device;
        return this;
    }

    public GlobalRequest withRequest(IRequest request) {
        this.request = request;
        return this;
    }

}
