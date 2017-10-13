package com.hellohasan.tenthclass.Network;

import com.hellohasan.tenthclass.RepositoryListShow.Repository;
import com.hellohasan.tenthclass.UserInfoShow.User;
import com.orhanobut.logger.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall implements GitHubApiService {

    @Override
    public void getUser(String userId, final ResponseCallback<User> callback) {

        RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<User> call = retrofitApiInterface.fetchUserFromServer(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Logger.d(response.raw());
                User user = response.body();
                if(response.code()==200)
                    callback.onSuccess(user);
                else
                    callback.onError(new Exception(response.message()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onError(t);
            }
        });

    }

    @Override
    public void getRepositoryList(String userId, final ResponseCallback<List<Repository>> callback) {
        RetrofitApiInterface retrofitApiInterface = RetrofitApiClient.getClient().create(RetrofitApiInterface.class);
        Call<List<Repository>> call = retrofitApiInterface.fetchRepositoryListFromServer(userId);

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if(response.code()==200)
                    callback.onSuccess(response.body());
                else
                    callback.onError(new Exception(response.message()));
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
