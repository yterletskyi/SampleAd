package yterletskyi.com.vunglesdk.sdk;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by yterletskyi on 17.08.17.
 */

public class FileFinder {

    public File findFile(File location, final String targetName) {
        File file;
        File[] files = location.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.equals(targetName);
            }
        });
        if (files.length > 0) {
            file = files[0];
        } else {
            file = null;
        }
        return file;
    }

}
