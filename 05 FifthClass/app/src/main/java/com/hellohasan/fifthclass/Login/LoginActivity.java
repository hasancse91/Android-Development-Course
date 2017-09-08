package com.hellohasan.fifthclass.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.hellohasan.fifthclass.NetworkClass.ApiInterface;
import com.hellohasan.fifthclass.IpAddress.MyIpActivity;
import com.hellohasan.fifthclass.R;
import com.hellohasan.fifthclass.NetworkClass.RetrofitApiClient;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.userName)
    EditText userNameEditText;
    @BindView(R.id.password)
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

    }

    //when click on sign in button. handled by ButterKnife library
    @OnClick(R.id.signInButton)
    void signInButtonClick(){
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        UserModel userModel = new UserModel(userName, password);

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<ResponseModel> call = apiInterface.loginToServer(userModel);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Logger.d("Raw Response: " + response.raw());

                if(response.code()==200){

                    ResponseModel responseModel = response.body();
                    Toast.makeText(getApplicationContext(), responseModel.getMessage(), Toast.LENGTH_LONG).show();

                    if(responseModel.isSuccess()) { // user name and password is correct
                        startActivity(new Intent(getApplicationContext(), MyIpActivity.class));
                        finish(); // finish LoginActivity
                    }

                } else
                    Toast.makeText(getApplicationContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Logger.d("Failed: " + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
