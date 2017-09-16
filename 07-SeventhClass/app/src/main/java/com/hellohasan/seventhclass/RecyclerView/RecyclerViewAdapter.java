package com.hellohasan.seventhclass.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hellohasan.seventhclass.Utils.Config;
import com.hellohasan.seventhclass.R;
import com.hellohasan.seventhclass.ShowMovieDetails.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public RecyclerViewAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {

        final Movie movie = movieList.get(position);

        Picasso.with(context)
                .load(movie.getImage())
                .into(holder.imageView);

        holder.movieNameTextView.setText(movie.getName());
        holder.movieRank.setText(String.valueOf(movie.getRating()));

        holder.movieNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Name: " + movie.getName(), Toast.LENGTH_LONG).show();
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.linearLayout.getContext(), MovieDetailsActivity.class);
                intent.putExtra(Config.MOVIE, movie);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
