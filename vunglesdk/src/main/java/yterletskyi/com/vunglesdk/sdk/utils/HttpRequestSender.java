package yterletskyi.com.vunglesdk.sdk.utils;

import yterletskyi.com.vunglesdk.sdk.api.IApiService;

/**
 * Created by yterletskyi on 17.08.17.
 */

public abstract class HttpRequestSender {

    private IApiService mApiService;
    private OnCompleteListener mOnCompleteListener;

    public HttpRequestSender(IApiService apiService) {
        mApiService = apiService;
    }

    public IApiService getApiService() {
        return mApiService;
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        mOnCompleteListener = onCompleteListener;
    }

    public void notifySuccess() {
        if (mOnCompleteListener != null) {
            mOnCompleteListener.onComplete();
        }
    }

    public void notifyFailure() {
        if (mOnCompleteListener != null) {
            mOnCompleteListener.onFailure();
        }
    }

}
