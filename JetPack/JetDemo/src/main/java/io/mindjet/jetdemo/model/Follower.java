package io.mindjet.jetdemo.model;

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

}
