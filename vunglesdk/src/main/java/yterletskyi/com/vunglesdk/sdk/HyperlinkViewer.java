package yterletskyi.com.vunglesdk.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by yterletskyi on 17.08.17.
 */

public class HyperlinkViewer {

    public void openViewIntent(Context context, String url) {
        Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        browseIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(browseIntent);
    }

}
