package com.hellohasan.sixthclass.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hellohasan.sixthclass.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder>{

    private List<Movie> movieList;

    public RecyclerViewAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Movie movie = movieList.get(position);

        Picasso.with(holder.imageView.getContext())
                .load(movie.getImage())
                .into(holder.imageView);

        holder.movieNameTextView.setText(movie.getName());
        holder.movieRank.setText(String.valueOf(movie.getRank()));

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
