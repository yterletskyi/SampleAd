package com.publisher.sample.sdk.vast;

/**
 * Created by yterletskyi on 05.08.17.
 */

public class VastDocument {

    private Tag mVast;

    public VastDocument(String version) {
        mVast = new Tag("VAST");
        mVast.addAttribute(
                new Attribute<>("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
        );
        mVast.addAttribute(
                new Attribute<>("xsi:noNamespaceSchemaLocation", "vast.xsd")
        );
        mVast.addAttribute(
                new Attribute<>("version", version)
        );
    }

    public VastDocument withAd(String id) {
        Tag adTag = new Tag("Ad");
        adTag.addAttribute(new Attribute<String>("id", id));
        mVast.addChild(adTag);
    }

    public VastDocument withInLine() {

    }


}
