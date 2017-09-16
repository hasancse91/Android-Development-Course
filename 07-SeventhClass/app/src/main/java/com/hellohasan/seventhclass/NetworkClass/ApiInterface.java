package com.hellohasan.seventhclass.NetworkClass;

import com.hellohasan.seventhclass.RecyclerView.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("movie_list.json")
    Call<MovieListResponse> getMovieList();

}