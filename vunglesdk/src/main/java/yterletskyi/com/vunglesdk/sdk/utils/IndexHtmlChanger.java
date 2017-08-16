package yterletskyi.com.vunglesdk.sdk.utils;

import java.io.File;

import yterletskyi.com.vunglesdk.sdk.PostVideoJavascriptInterface;
import yterletskyi.com.vunglesdk.sdk.utils.TextFileChanger;

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
