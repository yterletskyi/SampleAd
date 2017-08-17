package yterletskyi.com.vunglesdk.sdk.utils.hardware;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by yterletskyi on 17.08.17.
 */

public class AndroidId {

    public String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
