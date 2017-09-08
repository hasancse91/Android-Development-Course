package com.hellohasan.fifthclass.NetworkClass;

import com.hellohasan.fifthclass.IpAddress.IpModel;
import com.hellohasan.fifthclass.Login.ResponseModel;
import com.hellohasan.fifthclass.Login.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {

    @POST("login.php")
    Call<ResponseModel> loginToServer(@Body UserModel userModel);

    @GET
    Call<IpModel> getMyIp(@Url String url); //@Url for dynamic URL
}
