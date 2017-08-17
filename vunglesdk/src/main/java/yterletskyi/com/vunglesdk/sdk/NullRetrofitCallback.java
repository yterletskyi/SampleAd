package yterletskyi.com.vunglesdk.sdk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yterletskyi on 17.08.17.
 */

public class NullRetrofitCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
    }
}
