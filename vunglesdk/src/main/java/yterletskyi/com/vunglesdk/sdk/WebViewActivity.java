package yterletskyi.com.vunglesdk.sdk;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.File;

import yterletskyi.com.vunglesdk.R;

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        File html = (File) getIntent().getSerializableExtra("html");

        WebView webView = findViewById(R.id.web_view_root);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(html.toURI().toString());
        webView.addJavascriptInterface(new CompanionJavaScriptInterface(this), "Android");
    }

    static class CompanionJavaScriptInterface {

        private Activity mActivity;

        public CompanionJavaScriptInterface(Activity activity) {
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
}
