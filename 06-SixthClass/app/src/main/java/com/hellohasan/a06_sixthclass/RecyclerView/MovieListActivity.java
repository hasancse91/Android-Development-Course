package com.hellohasan.a06_sixthclass.RecyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.hellohasan.a06_sixthclass.R;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


public class MovieListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        Logger.addLogAdapter(new AndroidLogAdapter());

        Movie movie = new Movie();

        Gson gson = new Gson();

        String json = gson.toJson(movie, Movie.class);

        Logger.json(json);

    }
}
