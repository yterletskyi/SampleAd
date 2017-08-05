package com.publisher.sample.sdk.vast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yterletskyi on 05.08.17.
 */

public class Tag {

    private String mName;
    private List<Attribute> mAttributes;
    private List<Tag> mChildren;

    public Tag(String name) {
        mName = name;
        mChildren = new ArrayList<>();
        mAttributes = new ArrayList<>();
    }

    public void addAttribute(Attribute attribute) {
        mAttributes.add(attribute);
    }

    public List<Attribute> getAttributes() {
        return mAttributes;
    }

    public String getName() {
        return mName;
    }

    public List<Tag> getChildren() {
        return mChildren;
    }

    public void addChild(Tag child) {
        mChildren.add(child);
    }
}
