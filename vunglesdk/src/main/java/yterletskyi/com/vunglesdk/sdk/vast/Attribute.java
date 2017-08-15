package yterletskyi.com.vunglesdk.sdk.vast;

/**
 * Created by yterletskyi on 05.08.17.
 */

public class Attribute<T> {

    private String mName;
    private T mValue;

    public Attribute(String name, T value) {
        mName = name;
        mValue = value;
    }

    public String getName() {
        return mName;
    }

    public T getValue() {
        return mValue;
    }
}
