package yterletskyi.com.vunglesdk.sdk.model.request.reportad;

import com.google.gson.annotations.SerializedName;

public class Play {

    @SerializedName("startTime")
    public long startTime;
    @SerializedName("videoLength")
    public long videoLength;
    @SerializedName("videoViewed")
    public long videoViewed;

    public Play withStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Play withVideoLength(long videoLength) {
        this.videoLength = videoLength;
        return this;
    }

    public Play withVideoViewed(long videoViewed) {
        this.videoViewed = videoViewed;
        return this;
    }

}
