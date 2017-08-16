package yterletskyi.com.vunglesdk.sdk;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yterletskyi.com.vunglesdk.sdk.model.preload.response.Ad;
import yterletskyi.com.vunglesdk.sdk.model.preload.response.PlayPercentage;
import yterletskyi.com.vunglesdk.sdk.model.preload.response.PreloadResponse;
import yterletskyi.com.vunglesdk.sdk.utils.MimeDetector;
import yterletskyi.com.vunglesdk.sdk.vast.Attribute;
import yterletskyi.com.vunglesdk.sdk.vast.IChild;
import yterletskyi.com.vunglesdk.sdk.vast.Tag;
import yterletskyi.com.vunglesdk.sdk.vast.Value;
import yterletskyi.com.vunglesdk.sdk.vast.VastBuilder;
import yterletskyi.com.vunglesdk.sdk.vast.VastContract;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class VastCreator {

    // TODO: 05.08.17 no impression field specified
    // TODO: 05.08.17 no duration field specified

    public String build(PreloadResponse result) {
        Ad ad = result.ads.get(0);

        Map<Double, List<String>> quartiles = composeQuartilesMap(ad);

        String mimeType = new MimeDetector().getMimeForFile(ad.adMarkup.url);

        Tag vast = new Tag(VastContract.VAST)
                .withAttributes(
                        new Attribute<>("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance"),
                        new Attribute<>("xsi:noNamespaceSchemaLocation", "vast.xsd"),
                        new Attribute<>("version", "2.0")
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
                                                                                                                                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 0.0)),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "firstQuartile"))
                                                                                                                                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 0.25)),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "midpoint"))
                                                                                                                                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 0.5)),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "thirdQuartile"))
                                                                                                                                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 0.75)),
                                                                                                                        new Tag(VastContract.TRACKING)
                                                                                                                                .withAttributes(new Attribute<>("event", "complete"))
                                                                                                                                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 1.0)),
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

    private Map<Double, List<String>> composeQuartilesMap(Ad ad) {
        Map<Double, List<String>> quartiles = new HashMap<>();
        for (PlayPercentage playPercentage : ad.adMarkup.tpat.playPercentage) {
            quartiles.put(playPercentage.checkpoint, playPercentage.urls);
        }
        return quartiles;
    }

    private Value[] composeValuesForQuartile(Map<Double, List<String>> quartiles, double quartile) {
        List<String> urls = quartiles.get(quartile);
        int length = urls.size();
        Value[] urlArray = new Value[length];
        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i);
            Value value = new Value(url);
            urlArray[i] = value;
        }
        return urlArray;
    }

}
