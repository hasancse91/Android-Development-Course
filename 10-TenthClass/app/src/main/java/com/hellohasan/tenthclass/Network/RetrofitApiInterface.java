package com.hellohasan.tenthclass.Network;

import com.hellohasan.tenthclass.RepositoryListShow.Repository;
import com.hellohasan.tenthclass.UserInfoShow.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApiInterface {

    @GET("users/{user}")
    Call<User> fetchUserFromServer(@Path("user") String userId);

    @GET("users/{owner}/repos")
    Call<List<Repository>> fetchRepositoryListFromServer(@Path("owner") String userId);

}