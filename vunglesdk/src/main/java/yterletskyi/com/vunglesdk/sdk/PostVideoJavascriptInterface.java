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

    private OnPostrollListener mOnPostrollListener;

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
        mOnPostrollListener.onDownloadClicked();
    }

    private void replay() {
        mOnPostrollListener.onReplayClicked();
    }

    private void close() {
        mOnPostrollListener.onCloseClicked();
    }

    public void setOnPostrollListener(OnPostrollListener onPostrollListener) {
        mOnPostrollListener = onPostrollListener;
    }
}
