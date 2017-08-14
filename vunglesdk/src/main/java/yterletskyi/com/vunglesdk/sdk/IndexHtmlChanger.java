package yterletskyi.com.vunglesdk.sdk;

import java.io.File;

/**
 * Created by yterletskyi on 14.08.17.
 */

public class IndexHtmlChanger {

    private static final String TARGET = "return actionClicked(s);";
    private static final String REPLACEMENT = "return "
            + PostVideoJavascriptInterface.JAVASCRIPT_INTERFACE_NAME + ".actionClicked(s);";

    public void change(File indexHtmlChanger) {
        TextFileChanger textFileChanger = new TextFileChanger(indexHtmlChanger);
        textFileChanger.replaceInFile(TARGET, REPLACEMENT);
    }
}
