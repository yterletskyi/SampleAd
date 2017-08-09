package yterletskyi.com.vunglesdk.sdk;

/**
 * Created by yterletskyi on 26.07.17.
 */

public interface OnAdListener {

    void onAdLoaded();

    void onAdStarted();

    void onAdCompleted();

    void onAdClosed();

    void onAdFailedToLoad();

}
