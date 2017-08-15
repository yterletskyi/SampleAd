package yterletskyi.com.vunglesdk.sdk.model.request;

import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("ua")
    public String ua;
    @SerializedName("lmt")
    public int lmt;
    @SerializedName("make")
    public String make;
    @SerializedName("model")
    public String model;
    @SerializedName("os")
    public String os;
    @SerializedName("osv")
    public String osv;
    @SerializedName("h")
    public int h;
    @SerializedName("w")
    public int w;
    @SerializedName("carrier")
    public String carrier;
    @SerializedName("ifa")
    public String ifa;
    @SerializedName("ext")
    public Ext ext;

    public Device withUa(String ua) {
        this.ua = ua;
        return this;
    }

    public Device withLmt(int lmt) {
        this.lmt = lmt;
        return this;
    }

    public Device withMake(String make) {
        this.make = make;
        return this;
    }

    public Device withModel(String model) {
        this.model = model;
        return this;
    }

    public Device withOs(String os) {
        this.os = os;
        return this;
    }

    public Device withOsv(String osv) {
        this.osv = osv;
        return this;
    }

    public Device withH(int h) {
        this.h = h;
        return this;
    }

    public Device withW(int w) {
        this.w = w;
        return this;
    }

    public Device withCarrier(String carrier) {
        this.carrier = carrier;
        return this;
    }

    public Device withIfa(String ifa) {
        this.ifa = ifa;
        return this;
    }

    public Device withExt(Ext ext) {
        this.ext = ext;
        return this;
    }

}
