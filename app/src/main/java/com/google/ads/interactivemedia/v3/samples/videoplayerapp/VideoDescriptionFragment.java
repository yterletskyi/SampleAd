package com.google.ads.interactivemedia.v3.samples.videoplayerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.ads.interactivemedia.v3.samples.videoplayerapp.api.IApiService;
import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.requestad.RequestAd;
import com.google.ads.interactivemedia.v3.samples.videoplayerapp.model.preload.responsead.Response;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by yterletskyi on 25.07.17.
 */

/**
 * The fragment for displaying any video title or other non-video content.
 */
public class VideoDescriptionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_description, container, false);
        view.findViewById(R.id.btn_preload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preloadAd();
            }
        });
        return view;
    }

    private void preloadAd() {
        IApiService apiService = ((VideoApp) getActivity().getApplication()).getApiService();

        Call<Response> responseCall = apiService.preloadAd(new RequestAd());
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                System.out.println();
                // response.body().ads.get(0).adMarkup.url
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                System.out.println();
            }
        });

    }

}
