package com.hellohasan.a06_sixthclass.RecyclerView;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse implements Parcelable {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("message")
    private String message;
    @SerializedName("movies")
    private List<Movie> movies = null;
    public final static Parcelable.Creator<MovieListResponse> CREATOR = new Creator<MovieListResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieListResponse createFromParcel(Parcel in) {
            MovieListResponse instance = new MovieListResponse();
            instance.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.movies, (com.hellohasan.a06_sixthclass.RecyclerView.Movie.class.getClassLoader()));
            return instance;
        }

        public MovieListResponse[] newArray(int size) {
            return (new MovieListResponse[size]);
        }

    }
            ;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(message);
        dest.writeList(movies);
    }

    public int describeContents() {
        return 0;
    }

}