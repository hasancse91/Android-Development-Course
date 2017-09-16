package com.hellohasan.seventhclass.RecyclerView;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable
{

    @SerializedName("name")
    private String name;
    @SerializedName("director")
    private String director;
    @SerializedName("image")
    private String image;
    @SerializedName("rating")
    private Integer rating;
    @SerializedName("type")
    private String type;
    @SerializedName("plot")
    private String plot;
    public final static Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Movie createFromParcel(Parcel in) {
            Movie instance = new Movie();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.director = ((String) in.readValue((String.class.getClassLoader())));
            instance.image = ((String) in.readValue((String.class.getClassLoader())));
            instance.rating = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            instance.plot = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Movie[] newArray(int size) {
            return (new Movie[size]);
        }

    }
            ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(director);
        dest.writeValue(image);
        dest.writeValue(rating);
        dest.writeValue(type);
        dest.writeValue(plot);
    }

    public int describeContents() {
        return 0;
    }

}