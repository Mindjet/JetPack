package io.mindjet.jetdemo.viewmodel;

import android.support.v7.widget.GridLayoutManager;

import io.mindjet.jetdemo.databinding.ItemMovieBinding;
import io.mindjet.jetdemo.model.MovieList;
import io.mindjet.jetdemo.model.MovieItem;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;
import io.mindjet.jetgear.network.ServiceGen;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class MovieListViewModel extends RecyclerViewModel<ItemMovieBinding> {

    private GithubService service;

    public MovieListViewModel() {
        service = ServiceGen.create(GithubService.class);
    }

    @Override
    protected void initRecyclerView() {
        getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    @Override
    public void onLoadMore() {
        service.getMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieList>() {
                    @Override
                    public void call(MovieList movieList) {
                        for (MovieItem item : movieList.getData()) {
                            getAdapter().add(new MovieItemViewModel(item));
                        }
                        getAdapter().notifyDataSetChanged();
                        getAdapter().finishLoadMore(true);
                    }
                });
    }
}
