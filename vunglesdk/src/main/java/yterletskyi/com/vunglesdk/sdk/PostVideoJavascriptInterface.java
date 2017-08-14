package yterletskyi.com.vunglesdk.sdk;

import android.webkit.JavascriptInterface;

/**
 * Created by yterletskyi on 14.08.17.
 */

public class PostVideoJavascriptInterface {

    public static final String JAVASCRIPT_INTERFACE_NAME = "Android";
    private static final String ACTION_CLOSE = "close";
    private static final String ACTION_REPLAY = "replay";
    private static final String ACTION_DOWNLOAD = "download";

    private OnPostVideoCompanionListener mOnPostVideoCompanionListener;

    @JavascriptInterface
    public void actionClicked(String s) {
        switch (s) {
            case ACTION_CLOSE:
                close();
                break;
            case ACTION_REPLAY:
                replay();
                break;
            case ACTION_DOWNLOAD:
                download();
                break;
        }
    }

    private void download() {
        mOnPostVideoCompanionListener.onDownloadClicked();
    }

    private void replay() {
        mOnPostVideoCompanionListener.onReplayClicked();
    }

    private void close() {
        mOnPostVideoCompanionListener.onCloseClicked();
    }

    public void setOnPostVideoCompanionListener(OnPostVideoCompanionListener onPostVideoCompanionListener) {
        mOnPostVideoCompanionListener = onPostVideoCompanionListener;
    }
}
