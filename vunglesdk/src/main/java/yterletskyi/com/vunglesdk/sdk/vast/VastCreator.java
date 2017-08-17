package yterletskyi.com.vunglesdk.sdk.vast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yterletskyi.com.vunglesdk.sdk.model.response.preload.Ad;
import yterletskyi.com.vunglesdk.sdk.model.response.preload.PlayPercentage;
import yterletskyi.com.vunglesdk.sdk.utils.MimeDetector;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class VastCreator {

    // TODO: 05.08.17 no impression field specified
    // TODO: 05.08.17 no duration field specified

    public Tag composeVastTagForAd(Ad ad) {
        Map<Double, List<String>> quartiles = composeQuartilesMap(ad);

        String mimeType = new MimeDetector().getMimeForFile(ad.adMarkup.url);

        Attribute<String> xmlnsAttribute = new Attribute<>("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        Attribute<String> namespaceAtribute = new Attribute<>("xsi:noNamespaceSchemaLocation", "vast.xsd");
        Attribute<String> versionAttribute = new Attribute<>("version", "2.0");
        Attribute<String> id = new Attribute<>("id", ad.adMarkup.id);
        Tag adSystemTag = new Tag(VastContract.AD_SYSTEM)
                .withChildren(new Value("Vungle"));
        Tag adTitleTag = new Tag(VastContract.AD_TITLE)
                .withChildren(new Value("Vungle Ad"));
        Tag startEventTag = new Tag(VastContract.TRACKING)
                .withAttributes(new Attribute<>("event", "start"))
                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 0.0));
        Tag firstQuartileEventTag = new Tag(VastContract.TRACKING)
                .withAttributes(new Attribute<>("event", "firstQuartile"))
                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 0.25));
        Tag midpointEventTag = new Tag(VastContract.TRACKING)
                .withAttributes(new Attribute<>("event", "midpoint"))
                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 0.5));
        Tag thirdQuartileEventTag = new Tag(VastContract.TRACKING)
                .withAttributes(new Attribute<>("event", "thirdQuartile"))
                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 0.75));
        Tag completeEventTag = new Tag(VastContract.TRACKING)
                .withAttributes(new Attribute<>("event", "complete"))
                .withChildren((IChild[]) composeValuesForQuartile(quartiles, 1.0));
        Tag muteEventTag = new Tag(VastContract.TRACKING)
                .withAttributes(new Attribute<>("event", "mute"))
                .withChildren(new Value(ad.adMarkup.tpat.mute.get(0)));
        Tag unmuteEventTag = new Tag(VastContract.TRACKING)
                .withAttributes(new Attribute<>("event", "unmute"))
                .withChildren(new Value(ad.adMarkup.tpat.unmute.get(0)));
        Tag closeLinearEventTag = new Tag(VastContract.TRACKING)
                .withAttributes(new Attribute<>("event", "closeLinear"))
                .withChildren(new Value(ad.adMarkup.tpat.videoClose.get(0)));
        Tag trackingEventsTag = new Tag(VastContract.TRACKING_EVENTS)
                .withChildren(
                        startEventTag,
                        firstQuartileEventTag,
                        midpointEventTag,
                        thirdQuartileEventTag,
                        completeEventTag,
                        muteEventTag,
                        unmuteEventTag,
                        closeLinearEventTag
                );
        Attribute<Integer> widthAttribute = new Attribute<>("width", ad.adMarkup.videoWidth);
        Attribute<Integer> heightAttribute = new Attribute<>("height", ad.adMarkup.videoHeight);
        Attribute<String> mimeTypeAttribuute = new Attribute<>("type", mimeType);
        Attribute<String> deliveryAttribute = new Attribute<>("delivery", "progressive");
        Tag mediaFileTag = new Tag(VastContract.MEDIA_FILE)
                .withAttributes(
                        deliveryAttribute,
                        widthAttribute,
                        heightAttribute,
                        mimeTypeAttribuute
                )
                .withChildren(new Value(ad.adMarkup.url));
        Tag mediaFilesTag = new Tag(VastContract.MEDIA_FILES)
                .withChildren(mediaFileTag);
        Tag linearTag = new Tag(VastContract.LINEAR)
                .withChildren(trackingEventsTag, mediaFilesTag);
        Tag creativeTag = new Tag(VastContract.CREATIVE)
                .withChildren(linearTag);
        Tag creativesTag = new Tag(VastContract.CREATIVES)
                .withChildren(creativeTag);
        Tag inlineTag = new Tag(VastContract.IN_LINE)
                .withChildren(adSystemTag, adTitleTag, creativesTag);
        Tag adTag = new Tag(VastContract.AD)
                .withAttributes(id)
                .withChildren(inlineTag);
        Tag vast = new Tag(VastContract.VAST)
                .withAttributes(xmlnsAttribute, namespaceAtribute, versionAttribute)
                .withChildren(adTag);

        return vast;
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
