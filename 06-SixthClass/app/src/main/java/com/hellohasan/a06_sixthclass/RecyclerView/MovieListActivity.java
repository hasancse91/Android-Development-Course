package com.hellohasan.a06_sixthclass.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.hellohasan.a06_sixthclass.NetworkClass.ApiInterface;
import com.hellohasan.a06_sixthclass.NetworkClass.RetrofitApiClient;
import com.hellohasan.a06_sixthclass.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<MovieListResponse> call = apiInterface.getMovieList();

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieListResponse> call, @NonNull Response<MovieListResponse> response) {
                Logger.d("Raw HTTP response: " + response.raw());
                if(response.code()==200) {
                    MovieListResponse movieListResponse = response.body();
                    //Print response body in Log as JSON
                    Gson gson = new Gson();
                    String json = gson.toJson(movieListResponse, MovieListResponse.class);
                    Logger.json(json);
                    //End of JSON print in log

                    if(movieListResponse!=null && !movieListResponse.getMovies().isEmpty()) {
                        recyclerViewAdapter = new RecyclerViewAdapter(movieListResponse.getMovies());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(recyclerViewAdapter);
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {
                Logger.d("Failed: " + t.getMessage());
            }
        });



    }
}
