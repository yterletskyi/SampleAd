package yterletskyi.com.vunglesdk.sdk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by yterletskyi on 10.08.17.
 */

public class TextFileChanger {

    private static final String TEMP_FILE_NAME = "t1e2m3p";
    private File mFile;

    public TextFileChanger(File file) {
        mFile = file;
    }

    public void insertTextToFile(String textToInsert) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(mFile);
            String text = scanner.useDelimiter("\\A").next();
            int lastBracePosition = text.lastIndexOf("}");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }


    }

    private String writeFirstPart() {
        File file = new File(TEMP_FILE_NAME);


    }

    private void writeToFile() {

    }

    private void readSecondPart() {

    }

}
