package com.publisher.sample.sdk.utils;

import java.net.FileNameMap;
import java.net.URLConnection;

/**
 * Created by yterletskyi on 27.07.17.
 */

public class MimeDetector {

    public String getMimeForFile(String uri) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(uri);
    }

}
