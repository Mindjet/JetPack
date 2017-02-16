package io.mindjet.jetgear.network;

import java.util.List;

import io.mindjet.jetgear.model.Follower;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jet on 2/16/17.
 */

public interface GithubService {

    //https://api.github.com/users/JakeWharton/followers\?page\=5
    @GET("/users/JakeWharton/followers")
    Observable<List<Follower>> follower(@Query("page") int page, @Query("per_page") int perPage);

}
