package io.mindjet.jetdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ZZItem {

    @SerializedName("images")
    private List<String> images;
    @SerializedName("title")
    private String title;
    @SerializedName("id")
    private String id;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
