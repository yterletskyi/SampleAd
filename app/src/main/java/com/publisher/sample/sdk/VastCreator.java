package com.publisher.sample.sdk;

import com.jamesmurty.utils.XMLBuilder;
import com.publisher.sample.sdk.model.preload.response.Ad;
import com.publisher.sample.sdk.model.preload.response.PlayPercentage;
import com.publisher.sample.sdk.model.preload.response.PreloadResponse;
import com.publisher.sample.sdk.utils.MimeDetector;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yterletskyi on 26.07.17.
 */

public class VastCreator {

    private static final String REQUIRED_FIELD = "REQUIRED FIELD";

//    public String build(PreloadResponse result) {
//        Ad ad = result.ads.get(0);
//
//        Map<Double, String> quartiles = composeQuartilesMap(ad);
//
//        String mimeType = new MimeDetector().getMimeForFile(ad.adMarkup.url);
//
//        String xml = "";
//
//        try {
//
//            XMLBuilder xmlBuilder = XMLBuilder.create("VAST").a("version", "2.0")
//                    .e("Ad").a("id", ad.adMarkup.id)
//                    .e("InLine")
//                    .e("AdSystem").t("Vungle").up()
//                    .e("AdTitle").t("Vungle Ad").up()
//                    .e("Impression").cdata(REQUIRED_FIELD).up()
//                    .e("Creatives")
//                    .e("Creative")
//                    .e("Linear")
//                    .e("Duration").t(REQUIRED_FIELD).up()
//                    .e("TrackingEvents")
//                    .e("Tracking").a("event", "start").cdata(quartiles.get(0.0)).up()
//                    .e("Tracking").a("event", "firstQuartile").cdata(quartiles.get(0.25)).up()
//                    .e("Tracking").a("event", "midpoint").cdata(quartiles.get(0.5)).up()
//                    .e("Tracking").a("event", "thirdQuartile").cdata(quartiles.get(0.75)).up()
//                    .e("Tracking").a("event", "complete").cdata(quartiles.get(1.0)).up()
//                    .e("Tracking").a("event", "mute").cdata(ad.adMarkup.tpat.mute.get(0)).up()
//                    .e("Tracking").a("event", "unmute").cdata(ad.adMarkup.tpat.unmute.get(0)).up()
//                    .e("Tracking").a("event", "closeLinear").cdata(ad.adMarkup.tpat.videoClose.get(0)).up()
//                    .up()
//                    .e("MediaFiles")
//                    .e("MediaFile")
//                    .a("delivery", "progressive")
//                    .a("width", String.valueOf(ad.adMarkup.videoWidth))
//                    .a("height", String.valueOf(ad.adMarkup.videoHeight))
//                    .a("type", mimeType)
//                    .cdata(ad.adMarkup.url);
//
//            Properties outputProperties = new Properties();
//            outputProperties.put(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
//
//            xml = xmlBuilder.asString(outputProperties);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return xml;
//    }

//    private Map<Double, String> composeQuartilesMap(Ad ad) {
//        Map<Double, String> quartiles = new HashMap<>();
//        for (PlayPercentage playPercentage : ad.adMarkup.tpat.playPercentage) {
//            int urlIndex = 0;
//            quartiles.put(playPercentage.checkpoint, playPercentage.urls.get(urlIndex));
//        }
//        return quartiles;
//    }

}
