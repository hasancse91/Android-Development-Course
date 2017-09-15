package com.hellohasan.a06_sixthclass.NetworkClass;

import com.hellohasan.a06_sixthclass.RecyclerView.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("movie_list.json")
    Call<MovieListResponse> getMovieList();

}