package com.publisher.sample.sdk.vast;

import java.util.List;

/**
 * Created by yterletskyi on 05.08.17.
 */

public class Tag implements IChild<List<Attribute>> {

    private String mName;
    private List<Attribute> mAttributes;
    private List<IChild> mChildren;

    public Tag(String name) {
        mName = name;
    }

    public Tag withAttributes(List<Attribute> attributes) {
        mAttributes = attributes;
        return this;
    }

    public Tag withChildren(List<IChild> children) {
        mChildren = children;
        return this;
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

    public List<IChild> getChildren() {
        return mChildren;
    }

    public void addChild(Tag child) {
        mChildren.add(child);
    }

    @Override
    public List<Attribute> getValue() {
        return mAttributes;
    }
}
