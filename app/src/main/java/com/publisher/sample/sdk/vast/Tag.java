package com.publisher.sample.sdk.vast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yterletskyi on 05.08.17.
 */

public class Tag implements IChild {

    private String mName;
    private List<Attribute> mAttributes;
    private List<IChild> mChildren;

    public Tag(String name) {
        mName = name;
        mChildren = new ArrayList<>();
        mAttributes = new ArrayList<>();
    }

    public Tag withAttributes(Attribute... attributes) {
        mAttributes = Arrays.asList(attributes);
        return this;
    }

    public Tag withChildren(IChild... children) {
        mChildren = Arrays.asList(children);
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
