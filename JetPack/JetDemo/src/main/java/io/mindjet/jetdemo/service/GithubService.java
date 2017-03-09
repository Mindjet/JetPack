package io.mindjet.jetdemo.service;

import java.util.List;

import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetdemo.model.Movie;
import io.mindjet.jetdemo.model.ZZ;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jet on 2/16/17.
 */

public interface GithubService {

    //https://api.github.com/users/JakeWharton/followers?page=1&per_pave=5
    @GET("/users/{name}/followers")
    Observable<List<Follower>> follower(@Path("name") String name,
                                        @Query("page") int page,
                                        @Query("per_page") int perPage);

    @GET("http://news-at.zhihu.com/api/3/stories/latest")
    Observable<ZZ> getZZ();

    @GET("https://api-m.mtime.cn/Showtime/LocationMovies.api?locationId=290")
    Observable<Movie> getMovie();
}
