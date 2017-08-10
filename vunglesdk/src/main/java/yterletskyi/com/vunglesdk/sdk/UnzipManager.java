package yterletskyi.com.vunglesdk.sdk;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by yterletskyi on 09.08.17.
 */

public class UnzipManager {

    private File mZipFile;
    private File mLocation;

    public UnzipManager(File zipFile, File location) {
        mZipFile = zipFile;
        mLocation = location;
        makeDir("");
    }

    public void unzip() {
        try {
            FileInputStream fileInputStream = new FileInputStream(mZipFile);
            ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Log.i("VungleSdk", "Unzipping " + zipEntry.getName());

                if (zipEntry.isDirectory()) {
                    makeDir(zipEntry.getName());
                } else {
                    FileOutputStream fileOutputStream = new FileOutputStream(mLocation + "/" + zipEntry.getName());

                    byte[] buffer = new byte[8192];
                    int len;
                    while ((len = zipInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.close();

                    zipInputStream.closeEntry();
                }
            }
            zipInputStream.close();
        } catch (Exception e) {
            Log.i("VungleSdk", "unzip failed " + e.toString());
        }
    }

    private void makeDir(String dir) {
        File f = new File(mLocation + dir);
        if (!f.isDirectory()) {
            f.mkdirs();
        }
    }
}
