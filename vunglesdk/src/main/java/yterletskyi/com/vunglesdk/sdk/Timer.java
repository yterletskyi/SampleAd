package yterletskyi.com.vunglesdk.sdk;

/**
 * Created by yterletskyi on 17.08.17.
 */

public class Timer {

    private long mStartTime;

    public void start() {
        mStartTime = System.currentTimeMillis();
    }

    public long finish() {
        return System.currentTimeMillis() - mStartTime;
    }

}
