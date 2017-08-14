package yterletskyi.com.vunglesdk.sdk;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by yterletskyi on 10.08.17.
 */

public class TextFileChanger {

    private static final String ENCODING = "UTF-8";

    private File mFile;

    public TextFileChanger(File file) {
        mFile = file;
    }

    public void replaceInFile(String target, String replacement) {
        try {
            String content = FileUtils.readFileToString(mFile, ENCODING);
            content = content.replace(target, replacement);
            FileUtils.writeStringToFile(mFile, content, ENCODING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
