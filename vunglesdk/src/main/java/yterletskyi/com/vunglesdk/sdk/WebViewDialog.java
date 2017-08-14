package yterletskyi.com.vunglesdk.sdk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.webkit.WebView;

import java.io.File;

import yterletskyi.com.vunglesdk.R;

/**
 * Created by yterletskyi on 14.08.17.
 */

public class WebViewDialog extends Dialog {

    private File mIndexHtmlFile;
    private OnPostVideoCompanionListener mOnPostVideoCompanionListener;

    public WebViewDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public void setOnPostVideoCompanionListener(OnPostVideoCompanionListener onPostVideoCompanionListener) {
        mOnPostVideoCompanionListener = onPostVideoCompanionListener;
    }

    public void setIndexHtmlFile(File indexHtmlFile) {
        mIndexHtmlFile = indexHtmlFile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.web_view_root);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(mIndexHtmlFile.toURI().toString());
        PostVideoJavascriptInterface javascriptInterface = new PostVideoJavascriptInterface();
        javascriptInterface.setOnPostVideoCompanionListener(mOnPostVideoCompanionListener);
        webView.addJavascriptInterface(
                javascriptInterface, PostVideoJavascriptInterface.JAVASCRIPT_INTERFACE_NAME);

    }

}
