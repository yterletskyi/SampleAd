package yterletskyi.com.vunglesdk.sdk.utils.hardware;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class MyAudioManager {

    public boolean isSoundEnabled(Context context) {
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audio.getRingerMode() == AudioManager.RINGER_MODE_NORMAL;
    }

    public double getVolumeLevel(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        double currentLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        double maxLevel = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        return currentLevel / maxLevel;
    }

}
