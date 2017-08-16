package yterletskyi.com.vunglesdk.sdk.utils.hardware;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class DiskSpaceManager {

    public boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public long getAvailableExternalMemorySize() {
        if (externalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return -1;
        }
    }
}