package com.publisher.sample.sdk;

import com.publisher.sample.sdk.model.preload.response.Ad;
import com.publisher.sample.sdk.model.preload.response.PlayPercentage;
import com.publisher.sample.sdk.model.preload.response.PreloadResponse;
import com.publisher.sample.sdk.utils.MimeDetector;
import com.publisher.sample.sdk.vast.Attribute;
import com.publisher.sample.sdk.vast.Tag;
import com.publisher.sample.sdk.vast.Value;
import com.publisher.sample.sdk.vast.VastBuilder;
import com.publisher.sample.sdk.vast.VastContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class VastCreator {

    // TODO: 05.08.17 no impression field specified
    // TODO: 05.08.17 no duration field specified

    public String build(PreloadResponse result) {
        Ad ad = result.ads.get(0);

        Map<Double, String> quartiles = composeQuartilesMap(ad);

        String mimeType = new MimeDetector().getMimeForFile(ad.adMarkup.url);


        Tag vast = new Tag(VastContract.VAST)
                .withAttributes(
                        new Attribute<>("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance"),
                        new Attribute<>("xsi:noNamespaceSchemaLocation", "vast.xsd"),
                        new Attribute<>("version", "3.0")
                )
                .withChildren(
                        new Tag(VastContract.AD)
                                .withAttributes(
                                        new Attribute<>("id", ad.adMarkup.id)
                                )
                                .withChildren(
                                        new Tag(VastContract.IN_LINE)
                                                .withChildren(
                                                        new Tag(VastContract.AD_SYSTEM)
                                                                .withChildren(
                                                                        new Value("Vungle")
                                                                ),
                                                        new Tag(VastContract.AD_TITLE)
                                                                .withChildren(new Value("Vungle Ad")),
                                                        new Tag(VastContract.CREATIVES)
                                                                .withChildren(
                                                                        new Tag(VastContract.CREATIVE)
                                                                                .withChildren(
                                                                                        new Tag(VastContract.LINEAR)
                                                                                                .withChildren(
                                                                                                        new Tag(VastContract.TRACKING_EVENTS)
                                                                                                                .withChildren(
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "start"))
                                                                                                                                .withChildren(new Value(quartiles.get(0.0))),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "firstQuartile"))
                                                                                                                                .withChildren(new Value(quartiles.get(0.25))),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "midpoint"))
                                                                                                                                .withChildren(new Value(quartiles.get(0.5))),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "thirdQuartile"))
                                                                                                                                .withChildren(new Value(quartiles.get(0.75))),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "complete"))
                                                                                                                                .withChildren(new Value(quartiles.get(1.0))),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "mute"))
                                                                                                                                .withChildren(new Value(ad.adMarkup.tpat.mute.get(0))),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "unmute"))
                                                                                                                                .withChildren(new Value(ad.adMarkup.tpat.unmute.get(0))),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "closeLinear"))
                                                                                                                                .withChildren(new Value(ad.adMarkup.tpat.videoClose.get(0)))
                                                                                                                ),
                                                                                                        new Tag(VastContract.MEDIA_FILES)
                                                                                                                .withChildren(
                                                                                                                        new Tag(VastContract.MEDIA_FILE)
                                                                                                                                .withAttributes(
                                                                                                                                        new Attribute<>("delivery", "progressive"),
                                                                                                                                        new Attribute<>("width", ad.adMarkup.videoWidth),
                                                                                                                                        new Attribute<>("height", ad.adMarkup.videoHeight),
                                                                                                                                        new Attribute<>("type", mimeType)
                                                                                                                                )
                                                                                                                                .withChildren(
                                                                                                                                        new Value(ad.adMarkup.url)
                                                                                                                                )
                                                                                                                )
                                                                                                )
                                                                                )
                                                                )
                                                )

                                )
                );

        return new VastBuilder().buildFromTag(vast);
    }

    private Map<Double, String> composeQuartilesMap(Ad ad) {
        Map<Double, String> quartiles = new HashMap<>();
        for (PlayPercentage playPercentage : ad.adMarkup.tpat.playPercentage) {
            int urlIndex = 0;
            quartiles.put(playPercentage.checkpoint, playPercentage.urls.get(urlIndex));
        }
        return quartiles;
    }

}
