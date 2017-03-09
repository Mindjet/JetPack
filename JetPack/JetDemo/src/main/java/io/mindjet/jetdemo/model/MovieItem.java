package io.mindjet.jetdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class MovieItem {

    @SerializedName("commonSpecial")
    private String subtitle;
    @SerializedName("img")
    private String image;
    @SerializedName("tCn")
    private String name;
    @SerializedName("r")
    private String rating;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
