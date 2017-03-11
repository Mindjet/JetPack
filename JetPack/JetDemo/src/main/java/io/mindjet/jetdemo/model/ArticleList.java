package io.mindjet.jetdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ArticleList {

    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<ArticleItem> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ArticleItem> getStories() {
        return stories;
    }

    public void setStories(List<ArticleItem> stories) {
        this.stories = stories;
    }
}
