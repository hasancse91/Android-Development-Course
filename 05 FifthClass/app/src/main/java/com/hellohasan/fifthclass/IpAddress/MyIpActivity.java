package com.hellohasan.fifthclass.IpAddress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hellohasan.fifthclass.NetworkClass.ApiInterface;
import com.hellohasan.fifthclass.R;
import com.hellohasan.fifthclass.NetworkClass.RetrofitApiClient;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyIpActivity extends AppCompatActivity {

    @BindView(R.id.ipTextView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ip);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        getAndShowMyIpAddress();

    }

    private void getAndShowMyIpAddress(){
        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<IpModel> call = apiInterface.getMyIp("http://ip.jsontest.com/"); //set dynamic URL

        call.enqueue(new Callback<IpModel>() {
            @Override
            public void onResponse(Call<IpModel> call, Response<IpModel> response) {
                Logger.d(response.raw());
                if(response.code()==200){
                    IpModel ipModel = response.body();
                    textView.setText(String.valueOf(ipModel.getIp()));
                } else
                    textView.setText("Error: " + response.message());

            }

            @Override
            public void onFailure(Call<IpModel> call, Throwable t) {
                Logger.d("Failed:" + t.toString());
                textView.setText("Error: " + t.getMessage());
            }
        });
    }

}
