package com.hellohasan.seventhclass.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hellohasan.seventhclass.NetworkClass.ApiInterface;
import com.hellohasan.seventhclass.NetworkClass.RetrofitApiClient;
import com.hellohasan.seventhclass.R;
import com.hellohasan.seventhclass.Utils.Util;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressDialog progressDialog;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Movie list loading from server. Please wait...");

        if(Util.isInternetAvailable(this)){
            //Send GET request to server to fetch movie list AND load them into RecyclerView
            progressDialog.show();
            getMovieListFromServer();
        } else
            Toast.makeText(this, "Check your internet", Toast.LENGTH_LONG).show();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //when internet is available and user pull to refresh
                //Send GET request to server to fetch movie list AND load them into RecyclerView
                if(Util.isInternetAvailable(getApplicationContext()))
                    getMovieListFromServer();
                else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getApplicationContext(), "Check your internet", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void getMovieListFromServer() {
        ApiInterface apiInterface = RetrofitApiClient.getClient().create(ApiInterface.class);
        Call<MovieListResponse> call = apiInterface.getMovieList();

        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieListResponse> call, @NonNull Response<MovieListResponse> response) {

                if(progressDialog.isShowing()) progressDialog.dismiss();

                swipeRefreshLayout.setRefreshing(false);
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
                        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), movieListResponse.getMovies());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(recyclerViewAdapter);
                    } else
                        Toast.makeText(getApplicationContext(), "Movie list not found", Toast.LENGTH_LONG).show();

                } else
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<MovieListResponse> call, @NonNull Throwable t) {
                if(progressDialog.isShowing()) progressDialog.dismiss();

                swipeRefreshLayout.setRefreshing(false);
                Logger.d("Failed: " + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    //Call this method when user click on device's back button
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}