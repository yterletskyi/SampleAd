package com.publisher.sample.videoplayerapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.publisher.sample.R;
import com.publisher.sample.sdk.OnCompleteListener;
import com.publisher.sample.sdk.Sdk;
import com.publisher.sample.sdk.VastCreator;
import com.publisher.sample.sdk.model.preload.response.PreloadResponse;
import com.publisher.sample.videoplayerapp.api.IApiService;

import java.util.Arrays;

public class MyActivity extends AppCompatActivity {

    private Sdk mSdk;
    private VideoFragment mVideoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        mVideoFragment = (VideoFragment) supportFragmentManager.findFragmentById(R.id.video_fr);

        findViewById(R.id.btn_init).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSdk();
            }
        });

        findViewById(R.id.btn_preload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preloadAd("TESTREW28799");
            }
        });

        mSdk = new Sdk(this);
    }

    private void initSdk() {
        IApiService apiService = ((VideoApp) getApplication()).getApiService();

        mSdk.initSdk(apiService, "5916309cb46f6b5a3e00009c", Arrays.asList("DEFAULT32590", "TESTREW28799", "TESTINT07107"), new OnCompleteListener<Boolean>() {
            @Override
            public void onCompleted(Boolean result) {
                if (result) {
                    System.out.println();
                    enablePreloadAdButton();
                } else {
                    Toast.makeText(MyActivity.this, "Init error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void enablePreloadAdButton() {
        findViewById(R.id.btn_preload).setEnabled(true);
    }

    private void preloadAd(String placementId) {
        mSdk.preloadAd(placementId, new OnCompleteListener<PreloadResponse>() {
            @Override
            public void onCompleted(PreloadResponse result) {
                formVastXml(result);
            }
        });
    }

    private void formVastXml(PreloadResponse result) {
        VastCreator vastCreator = new VastCreator();
//        String xml = vastCreator.build(result);
        String xml =
                "<VAST version=\"2.0\">\n" +
                        "\t<Ad id=\"5979ec4b80aedd125355720a\">\n" +
                        "\t\t<InLine>\n" +
                        "\t\t\t<AdSystem>DSAF</AdSystem>\n" +
                        "\t\t\t<AdTitle>REQUIRED FIELD</AdTitle>\n" +
                        "\t\t\t<Impression>\n" +
                        "\t\t\t\t<![CDATA[asd]]>\n" +
                        "\t\t\t</Impression>\n" +
                        "\t\t\t<Creatives>\n" +
                        "\t\t\t\t<Creative>\n" +
                        "\t\t\t\t\t<Linear>\n" +
                        "\t\t\t\t\t\t<Duration>00:00:10</Duration>\n" +
                        "\t\t\t\t\t\t<TrackingEvents>\n" +
                        "\t\t\t\t\t\t\t<Tracking event=\"start\">\n" +
                        "\t\t\t\t\t\t\t\t<![CDATA[https://ingest.vungle.com/tpat?event_id=5979ec4b80aedd125355720a&device_id=d3ca6224c70c7219&event_type=PERCENTAGE&play_percentage=0]]>\n" +
                        "\t\t\t\t\t\t\t</Tracking>\n" +
                        "\t\t\t\t\t\t\t<Tracking event=\"firstQuartile\">\n" +
                        "\t\t\t\t\t\t\t\t<![CDATA[https://ingest.vungle.com/tpat?event_id=5979ec4b80aedd125355720a&device_id=d3ca6224c70c7219&event_type=PERCENTAGE&play_percentage=0.25]]>\n" +
                        "\t\t\t\t\t\t\t</Tracking>\n" +
                        "\t\t\t\t\t\t\t<Tracking event=\"midpoint\">\n" +
                        "\t\t\t\t\t\t\t\t<![CDATA[https://ingest.vungle.com/tpat?event_id=5979ec4b80aedd125355720a&device_id=d3ca6224c70c7219&event_type=PERCENTAGE&play_percentage=0.5]]>\n" +
                        "\t\t\t\t\t\t\t</Tracking>\n" +
                        "\t\t\t\t\t\t\t<Tracking event=\"thirdQuartile\">\n" +
                        "\t\t\t\t\t\t\t\t<![CDATA[https://ingest.vungle.com/tpat?event_id=5979ec4b80aedd125355720a&device_id=d3ca6224c70c7219&event_type=PERCENTAGE&play_percentage=0.75]]>\n" +
                        "\t\t\t\t\t\t\t</Tracking>\n" +
                        "\t\t\t\t\t\t\t<Tracking event=\"complete\">\n" +
                        "\t\t\t\t\t\t\t\t<![CDATA[https://ingest.vungle.com/tpat?event_id=5979ec4b80aedd125355720a&device_id=d3ca6224c70c7219&event_type=PERCENTAGE&play_percentage=1]]>\n" +
                        "\t\t\t\t\t\t\t</Tracking>\n" +
                        "\t\t\t\t\t\t\t<Tracking event=\"mute\">\n" +
                        "\t\t\t\t\t\t\t\t<![CDATA[https://ingest.vungle.com/tpat?event_id=5979ec4b80aedd125355720a&device_id=d3ca6224c70c7219&event_type=MUTE]]>\n" +
                        "\t\t\t\t\t\t\t</Tracking>\n" +
                        "\t\t\t\t\t\t\t<Tracking event=\"unmute\">\n" +
                        "\t\t\t\t\t\t\t\t<![CDATA[https://ingest.vungle.com/tpat?event_id=5979ec4b80aedd125355720a&device_id=d3ca6224c70c7219&event_type=UNMUTE]]>\n" +
                        "\t\t\t\t\t\t\t</Tracking>\n" +
                        "\t\t\t\t\t\t\t<Tracking event=\"closeLinear\">\n" +
                        "\t\t\t\t\t\t\t\t<![CDATA[https://ingest.vungle.com/tpat?event_id=5979ec4b80aedd125355720a&device_id=d3ca6224c70c7219&event_type=VIDEO_CLOSE]]>\n" +
                        "\t\t\t\t\t\t\t</Tracking>\n" +
                        "\t\t\t\t\t\t</TrackingEvents>\n" +
                        "\t\t\t\t\t\t<MediaFiles>\n" +
                        "\t\t\t\t\t\t\t<MediaFile delivery=\"progressive\" width=\"960\" height=\"540\" type=\"video/mp4\">\n" +
                        "\t\t\t\t\t\t\t\t<![CDATA[https://cdn-lb.vungle.com/zen/GP0004_MCOC_mashup-DP-Hulk-SG-IF-SL-MagC_30s_1920x1080_UI_duo-960x540-Q2.mp4]]>\n" +
                        "\t\t\t\t\t\t\t</MediaFile>\n" +
                        "\t\t\t\t\t\t</MediaFiles>\n" +
                        "\t\t\t\t\t</Linear>\n" +
                        "\t\t\t\t</Creative>\n" +
                        "\t\t\t</Creatives>\n" +
                        "\t\t</InLine>\n" +
                        "\t</Ad>\n" +
                        "</VAST>";

        setVastXml(xml);
        enablePlayButton();
    }

    public void setVastXml(String xml) {
        mVideoFragment.setVastXml(xml);
    }

    public void enablePlayButton() {
        mVideoFragment.enablePlayButton();
    }

}
