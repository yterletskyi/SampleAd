package yterletskyi.com.vunglesdk.sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by yterletskyi on 17.08.17.
 */

public class SharedPreferenceManager {

    private static final String IS_FIRST_LAUNCH = "is_first_sdk_launch";
    private SharedPreferences mSharedPreferences;

    public SharedPreferenceManager(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isFirstLaunch() {
        return mSharedPreferences.getBoolean(IS_FIRST_LAUNCH, true);
    }

    public void setFirstLaunch(boolean b) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(IS_FIRST_LAUNCH, b);
        editor.apply();
    }

}
