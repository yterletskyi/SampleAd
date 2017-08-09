package yterletskyi.com.vunglesdk.sdk.vast;

/**
 * Created by yterletskyi on 05.08.17.
 */

public class Value implements IChild<String> {

    private String mValue;

    public Value(String value) {
        mValue = value;
    }

    @Override
    public String getValue() {
        return mValue;
    }
}
