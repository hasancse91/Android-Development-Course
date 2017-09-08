package com.hellohasan.forthclass.NetworkRelatedClass;

import com.hellohasan.forthclass.ModelClass.DataModel;
import com.hellohasan.forthclass.ModelClass.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("rest.php") //REST method type POST and route is `rest.php`
    Call<ResponseModel> sendUserData(@Body DataModel dataModel);//object of DataModel will be sent, ResponseModel will be received
}
