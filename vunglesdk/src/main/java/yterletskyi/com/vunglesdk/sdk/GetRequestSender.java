package yterletskyi.com.vunglesdk.sdk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yterletskyi.com.vunglesdk.sdk.api.IApiService;

/**
 * Created by yterletskyi on 17.08.17.
 */

public class GetRequestSender extends HttpRequestSender {

    public GetRequestSender(IApiService apiService) {
        super(apiService);
    }

    public void send(String url) {
        Call<Void> call = getApiService().sendGetRequest(url);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                notifySuccess();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                notifyFailure();
            }
        });
    }


}
