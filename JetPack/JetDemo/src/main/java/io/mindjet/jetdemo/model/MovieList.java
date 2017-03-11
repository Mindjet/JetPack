package io.mindjet.jetdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class MovieList {

    @SerializedName("date")
    private String date;
    @SerializedName("ms")
    private List<MovieItem> data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MovieItem> getData() {
        return data;
    }

    public void setData(List<MovieItem> data) {
        this.data = data;
    }
}
