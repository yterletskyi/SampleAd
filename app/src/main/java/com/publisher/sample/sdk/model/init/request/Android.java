
package com.publisher.sample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Android {

    @SerializedName("volume_level")
    @Expose
    public double volumeLevel;
    @SerializedName("battery_level")
    @Expose
    public double batteryLevel;
    @SerializedName("battery_saver_enabled")
    @Expose
    public int batterySaverEnabled;
    @SerializedName("battery_state")
    @Expose
    public String batteryState;
    @SerializedName("storage_bytes_available")
    @Expose
    public int storageBytesAvailable;
    @SerializedName("connection_type")
    @Expose
    public String connectionType;
    @SerializedName("connection_type_detail")
    @Expose
    public String connectionTypeDetail;
    @SerializedName("network_metered")
    @Expose
    public int networkMetered;
    @SerializedName("data_saver_status")
    @Expose
    public String dataSaverStatus;
    @SerializedName("sd_card_available")
    @Expose
    public int sdCardAvailable;
    @SerializedName("sound_enabled")
    @Expose
    public int soundEnabled;
    @SerializedName("language")
    @Expose
    public String language;
    @SerializedName("locale")
    @Expose
    public String locale;
    @SerializedName("android_id")
    @Expose
    public String androidId;
    @SerializedName("vduid")
    @Expose
    public String vduid;
    @SerializedName("os_name")
    @Expose
    public String osName;
    @SerializedName("time_zone")
    @Expose
    public String timeZone;

    public Android withVolumeLevel(double volumeLevel) {
        this.volumeLevel = volumeLevel;
        return this;
    }

    public Android withBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
        return this;
    }

    public Android withBatterySaverEnabled(int batterySaverEnabled) {
        this.batterySaverEnabled = batterySaverEnabled;
        return this;
    }

    public Android withBatteryState(String batteryState) {
        this.batteryState = batteryState;
        return this;
    }

    public Android withStorageBytesAvailable(int storageBytesAvailable) {
        this.storageBytesAvailable = storageBytesAvailable;
        return this;
    }

    public Android withConnectionType(String connectionType) {
        this.connectionType = connectionType;
        return this;
    }

    public Android withConnectionTypeDetail(String connectionTypeDetail) {
        this.connectionTypeDetail = connectionTypeDetail;
        return this;
    }

    public Android withNetworkMetered(int networkMetered) {
        this.networkMetered = networkMetered;
        return this;
    }

    public Android withDataSaverStatus(String dataSaverStatus) {
        this.dataSaverStatus = dataSaverStatus;
        return this;
    }

    public Android withSdCardAvailable(int sdCardAvailable) {
        this.sdCardAvailable = sdCardAvailable;
        return this;
    }

    public Android withSoundEnabled(int soundEnabled) {
        this.soundEnabled = soundEnabled;
        return this;
    }

    public Android withLanguage(String language) {
        this.language = language;
        return this;
    }

    public Android withLocale(String locale) {
        this.locale = locale;
        return this;
    }

    public Android withAndroidId(String androidId) {
        this.androidId = androidId;
        return this;
    }

    public Android withVduid(String vduid) {
        this.vduid = vduid;
        return this;
    }

    public Android withOsName(String osName) {
        this.osName = osName;
        return this;
    }

    public Android withTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

}
