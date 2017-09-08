package com.hellohasan.forthclass.NetworkRelatedClass;

import android.support.annotation.NonNull;

import com.hellohasan.forthclass.UiClass.CallBackInterface;
import com.hellohasan.forthclass.ModelClass.DataModel;
import com.hellohasan.forthclass.ModelClass.ResponseModel;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkCall {
    private CallBackInterface callBackInterface;

    public NetworkCall(CallBackInterface callBackInterface) {
        //TextView of MainActivity will be updated through this interface
        this.callBackInterface = callBackInterface;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    //send data to server and receive response from server
    public void sendData(DataModel dataModel){
        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> call = apiInterface.sendUserData(dataModel);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                ResponseModel responseModel = response.body(); //extract expected object from `response`
                Logger.d("Response: " + response);
                Logger.d("Response: " + responseModel.getMessage());
                callBackInterface.uiUpdate(responseModel); //call to update TextView of MainActivity
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Logger.d("Failure Response: " + t.toString());

                //call to update TextView of MainActivity
                callBackInterface.uiUpdate(new ResponseModel(false, t.getMessage()));
            }
        });
    }
}
