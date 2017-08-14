package yterletskyi.com.vunglesdk.sdk;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by yterletskyi on 14.08.17.
 */

public class PostVideoJavascriptInterface {

    public static final String JAVASCRIPT_INTERFACE_NAME = "Android";

    private Activity mActivity;

    public PostVideoJavascriptInterface(Activity activity) {
        mActivity = activity;
    }

    @JavascriptInterface
    public void actionClicked(String s) {
        Toast.makeText(mActivity, s, Toast.LENGTH_SHORT).show();
        if (s.equals("close")) {
            mActivity.finish();
        }
    }


}
