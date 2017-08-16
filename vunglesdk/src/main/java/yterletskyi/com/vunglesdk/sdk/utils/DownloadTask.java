package yterletskyi.com.vunglesdk.sdk.utils;

import android.os.AsyncTask;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by yterletskyi on 09.08.17.
 */

public class DownloadTask extends AsyncTask<Void, Void, File> {

    private String mUrl;
    private File mDestinationFile;
    private OnDownloadListener mOnDownloadListener;

    public DownloadTask(String url, File destinationFile) {
        mUrl = url;
        mDestinationFile = destinationFile;
    }

    public void setOnDownloadListener(OnDownloadListener onDownloadListener) {
        mOnDownloadListener = onDownloadListener;
    }

    @Override
    protected File doInBackground(Void... voids) {
        return downloadFile(mUrl, mDestinationFile);
    }

    private File downloadFile(String url, File outputFile) {
        try {
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            int contentLength = conn.getContentLength();

            InputStream inputStream = u.openStream();
            DataInputStream stream = new DataInputStream(inputStream);

            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();

            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            DataOutputStream dos = new DataOutputStream(fileOutputStream);
            dos.write(buffer);
            dos.flush();
            dos.close();
            return outputFile;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(File file) {
        if (file != null) {
            mOnDownloadListener.onDownloadCompleted(file);
        } else {
            mOnDownloadListener.onDownloadFailed();
        }
    }

    public interface OnDownloadListener {
        void onDownloadCompleted(File file);

        void onDownloadFailed();
    }

}
