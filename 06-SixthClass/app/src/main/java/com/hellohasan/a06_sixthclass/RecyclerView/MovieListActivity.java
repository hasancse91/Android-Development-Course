package com.hellohasan.a06_sixthclass.RecyclerView;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

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

        //Send GET request to server to fetch movie list AND load them into RecyclerView
        getMovieListFromServer();


    }


    private void getMovieListFromServer() {
        Toast.makeText(this, "Movie list is loading from server...", Toast.LENGTH_LONG).show();

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

                    //If movie list found, load them to RecyclerView
                    if(movieListResponse!=null && !movieListResponse.getMovies().isEmpty()) {
                        recyclerViewAdapter = new RecyclerViewAdapter(movieListResponse.getMovies());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(recyclerViewAdapter);
                    } else
                        Toast.makeText(getApplicationContext(), "Movie list not found", Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {
                Logger.d("Failed: " + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}