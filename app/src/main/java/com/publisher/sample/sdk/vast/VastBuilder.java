package com.publisher.sample.sdk.vast;

import android.webkit.URLUtil;

import com.jamesmurty.utils.XMLBuilder;

import java.util.Properties;

/**
 * Created by yterletskyi on 05.08.17.
 */

public class VastBuilder {

    public String buildFromTag(Tag tag) {

        String xml = "";

        try {

            XMLBuilder xmlBuilder = XMLBuilder.create(tag.getName());
            fillAttributes(xmlBuilder, tag);
            fillChildren(xmlBuilder, tag);

            Properties outputProperties = new Properties();
            outputProperties.put(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");

            xml = xmlBuilder.asString(outputProperties);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return xml;
    }

    private XMLBuilder fillChildren(XMLBuilder xmlBuilder, Tag tag) {
        for (IChild child : tag.getChildren()) {
            if (child instanceof Tag) {
                fillTagChildren(xmlBuilder, (Tag) child);
            } else if (child instanceof Value) {
                fillValueChildren(xmlBuilder, (Value) child);
            } else
                throw new IllegalArgumentException();
        }
        return xmlBuilder;
    }

    private void fillTagChildren(XMLBuilder xmlBuilder, Tag tagChild) {
        XMLBuilder builder = xmlBuilder.e(tagChild.getName());
        fillAttributes(builder, tagChild);
        fillChildren(builder, tagChild);
    }

    private void fillValueChildren(XMLBuilder xmlBuilder, Value valueChild) {
        String value = valueChild.getValue();
        if (URLUtil.isValidUrl(value)) {
            xmlBuilder.cdata(value);
        } else {
            xmlBuilder.t(value);
        }
    }

    private XMLBuilder fillAttributes(XMLBuilder xmlBuilder, Tag tag) {
        for (Attribute attribute : tag.getAttributes()) {
            // TODO: 05.08.17 check toString() here
            xmlBuilder.a(attribute.getName(), attribute.getValue().toString());
        }
        return xmlBuilder;
    }

}
