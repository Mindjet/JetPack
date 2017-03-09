package io.mindjet.jetdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ZZ {

    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<ZZItem> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ZZItem> getStories() {
        return stories;
    }

    public void setStories(List<ZZItem> stories) {
        this.stories = stories;
    }
}
