package yterletskyi.com.vunglesdk.sdk.model;

import yterletskyi.com.vunglesdk.sdk.Timer;

/**
 * Created by yterletskyi on 17.08.17.
 */

public class TimeMeasuerer {

    private Timer mAdViewTimer;
    private Timer mVideoLengthTimer;

    public void startAdViewTimer() {
        mAdViewTimer = new Timer();
        mAdViewTimer.start();
    }

    public long finishAdViewTimer() {
        return mAdViewTimer.finish();
    }

    public void startVideoLengthTimer() {
        mVideoLengthTimer = new Timer();
        mVideoLengthTimer.start();
    }

    public long finishVideoLengthTimer() {
        return mVideoLengthTimer.finish();
    }

}
