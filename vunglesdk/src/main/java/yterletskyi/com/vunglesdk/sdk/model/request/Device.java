package yterletskyi.com.vunglesdk.sdk.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("ua")
    @Expose
    public String ua;
    @SerializedName("lmt")
    @Expose
    public int lmt;
    @SerializedName("make")
    @Expose
    public String make;
    @SerializedName("model")
    @Expose
    public String model;
    @SerializedName("os")
    @Expose
    public String os;
    @SerializedName("osv")
    @Expose
    public String osv;
    @SerializedName("h")
    @Expose
    public int h;
    @SerializedName("w")
    @Expose
    public int w;
    @SerializedName("carrier")
    @Expose
    public String carrier;
    @SerializedName("ifa")
    @Expose
    public String ifa;
    @SerializedName("ext")
    @Expose
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
