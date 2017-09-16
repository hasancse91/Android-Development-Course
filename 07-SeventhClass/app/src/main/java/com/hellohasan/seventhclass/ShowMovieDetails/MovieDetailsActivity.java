package com.hellohasan.seventhclass.ShowMovieDetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hellohasan.seventhclass.R;
import com.hellohasan.seventhclass.RecyclerView.Movie;
import com.hellohasan.seventhclass.Utils.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Movie movie = getIntent().getParcelableExtra(Config.MOVIE);

        /*
        Print the movie object as JSON in log message.
        Just for checking the data is valid or not
         */
        Gson gson = new Gson();
        String movieJson = gson.toJson(movie, Movie.class);
        Logger.json(movieJson);
        //End of log message print

        Picasso.with(this)
                .load(movie.getImage())
                .into(imageView);

        movieNameTextView.setText(movie.getName());
        directorNameTextView.setText(movie.getDirector());
        movieTypeTextView.setText(movie.getType());
        ratingTextView.setText(String.valueOf(movie.getRating()));
        plotTextView.setText(movie.getPlot());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
