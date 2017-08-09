package yterletskyi.com.vunglesdk.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class Connectivity {

    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public static String getConnectionTypeDetail(Context context) {
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        int type = info.getType();
        int subtype = info.getSubtype();

        if (type == ConnectivityManager.TYPE_WIFI) {
            return "WIFI";
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subtype) {
                case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                    return "3g"; // ~ 10-20 Mbps
                case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                    return "2g"; // ~25 kbps
                case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                    return "4g"; // ~ 10+ Mbps
                // Unknown
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                default:
                    return "Not Found";
            }
        } else {
            return "Not Found";
        }
    }

    public static String getConnectionType(Context context) {
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        int type = info.getType();
        String res;
        if (type == ConnectivityManager.TYPE_WIFI) {
            res = "WIFI";
        } else {
            res = "MOBILE";
        }
        return res;
    }

    public static boolean isNetworkMetered(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return cm.isActiveNetworkMetered();
        } else {
            return false;
        }
    }

    public static String getDataSavedStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            int status = cm.getRestrictBackgroundStatus();
            switch (status) {
                case ConnectivityManager.RESTRICT_BACKGROUND_STATUS_DISABLED:
                    return "DISABLED";
                case ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED:
                    return "ENABLED";
                case ConnectivityManager.RESTRICT_BACKGROUND_STATUS_WHITELISTED:
                    return "WHITELIST";
                default:
                    return "UNKNOWN";
            }
        } else {
            return "NOT_APPLICABLE";
        }
    }

}
