package com.hellohasan.seventhclass.ShowMovieDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hellohasan.seventhclass.Utils.Config;
import com.hellohasan.seventhclass.R;
import com.hellohasan.seventhclass.RecyclerView.Movie;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.movieImageView)
    ImageView imageView;
    @BindView(R.id.movieName)
    TextView movieNameTextView;
    @BindView(R.id.directorNameTextView)
    TextView directorNameTextView;
    @BindView(R.id.movieTypeTextView)
    TextView movieTypeTextView;
    @BindView(R.id.ratingTextView)
    TextView ratingTextView;
    @BindView(R.id.plotTextView)
    TextView plotTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        Logger.addLogAdapter(new AndroidLogAdapter());

        Movie movie = getIntent().getParcelableExtra(Config.MOVIE);

        /*
        Print the movie object as JSON in log message.
        Just for checking the data is valid or not
         */
        Gson gson = new Gson();
        String movieJson = gson.toJson(movie, Movie.class);
        Logger.json(movieJson);
        //End of log message print




    }
}
