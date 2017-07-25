package com.google.ads.interactivemedia.v3.samples.videoplayerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yterletskyi on 25.07.17.
 */

/**
 * The fragment for displaying any video title or other non-video content.
 */
public class VideoDescriptionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_description, container, false);
    }

}
