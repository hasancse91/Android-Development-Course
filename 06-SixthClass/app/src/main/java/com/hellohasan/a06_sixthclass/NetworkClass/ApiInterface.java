package com.hellohasan.a06_sixthclass.NetworkClass;

import com.hellohasan.a06_sixthclass.RecyclerView.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("movie_list.json")
    Call<MovieListResponse> getMovieList();

}

/*
    @Part List<MultipartBody.Part> images
    https://github.com/square/retrofit/issues/1776
    https://stackoverflow.com/questions/39866676/retrofit-uploading-multiple-images-to-a-single-key
*/