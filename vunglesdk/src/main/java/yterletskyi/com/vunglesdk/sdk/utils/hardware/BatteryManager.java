package yterletskyi.com.vunglesdk.sdk.utils.hardware;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class BatteryManager {

    public double getBatteryPercentage(Context context) {

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, iFilter);

        int level = batteryStatus != null ? batteryStatus.getIntExtra(android.os.BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ? batteryStatus.getIntExtra(android.os.BatteryManager.EXTRA_SCALE, -1) : -1;

        return level / (double) scale;
    }


    public int getBatterySaverEnabled(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && powerManager.isPowerSaveMode()) {
            return 1;
        }
        return 0;
    }

    public boolean isCharging(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int plugged = intent.getIntExtra(android.os.BatteryManager.EXTRA_PLUGGED, -1);
        return plugged == android.os.BatteryManager.BATTERY_PLUGGED_AC || plugged == android.os.BatteryManager.BATTERY_PLUGGED_USB;
    }

}
