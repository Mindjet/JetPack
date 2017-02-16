package io.mindjet.jetgear.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jet on 2/16/17.
 */

public class Follower {

    @SerializedName("login")
    public String name;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("html_url")
    public String htmlUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}
