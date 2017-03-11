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

    @SerializedName("location")
    public String location;
    @SerializedName("bio")
    public String bio;
    @SerializedName("public_repos")
    public String publicRepos;
    @SerializedName("followers")
    public String followers;
    @SerializedName("email")
    public String email;

}
