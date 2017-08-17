package yterletskyi.com.vunglesdk.sdk.utils;

import android.app.Activity;
import android.content.pm.ActivityInfo;

/**
 * Created by yterletskyi on 17.08.17.
 */

public class ScreenRotator {

    private Activity mActivity;

    public ScreenRotator(Activity activity) {
        mActivity = activity;
    }

    public void rotateToPortrait() {
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void rotateToLandscape() {
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
