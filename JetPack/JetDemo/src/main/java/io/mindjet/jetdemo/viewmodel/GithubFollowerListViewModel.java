package io.mindjet.jetdemo.viewmodel;

import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.listener.IFollowerListener;
import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.mvvm.viewmodel.list.SwipeRecyclerViewModel;
import io.mindjet.jetgear.network.ServiceGen;
import io.mindjet.jetgear.reactivex.RxActions;
import io.mindjet.jetgear.reactivex.rxbus.RxBus;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jet on 2/20/17.
 */

public class GithubFollowerListViewModel extends SwipeRecyclerViewModel {

    private String userName;
    private GithubService service;
    private int page = 1;
    private Subscription followerSub;

    private IFollowerListener iFollowerListener;

    public GithubFollowerListViewModel() {
        this("JakeWharton");
    }

    public GithubFollowerListViewModel(String userName) {
        this.userName = userName;
        service = ServiceGen.create(GithubService.class);
    }

    public GithubFollowerListViewModel callback(IFollowerListener iFollowerListener) {
        this.iFollowerListener = iFollowerListener;
        return this;
    }

    @Override
    protected void afterComponentsBound() {
        getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        getRecyclerView().setBackground(getContext().getResources().getDrawable(R.color.rcv_gray_light));
        changePbColor(R.color.colorPrimary);
    }

    @Override
    public void onRefresh() {
        page = 1;
        getFollowerList();
    }

    @Override
    public void onLoadMore() {
        getFollowerList();
    }

    private void getFollowerList() {
        followerSub = service.follower(userName, page, 10)
                .throttleLast(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(RxActions.clear(getAdapter(), page == 1))
                .flatMap(new Func1<List<Follower>, Observable<Follower>>() {
                    @Override
                    public Observable<Follower> call(List<Follower> followers) {
                        return Observable.from(followers);
                    }
                })
                .map(new Func1<Follower, GithubFollowerViewModel>() {
                    @Override
                    public GithubFollowerViewModel call(Follower follower) {
                        return new GithubFollowerViewModel(follower).callback(iFollowerListener);
                    }
                })
                .toList()
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        hideRefreshing();
                    }
                })
                .subscribe(new Action1<List<GithubFollowerViewModel>>() {
                    @Override
                    public void call(List<GithubFollowerViewModel> list) {
                        page += 1;
                        getAdapter().addAll(list);
                        getAdapter().notifyItemRangeInserted(getAdapter().size(), list.size());
                        getAdapter().onFinishLoadMore(list.size() == 0);
                    }
                }, RxActions.onError());
    }

    @Override
    public void onDestroy() {
        RxBus.unSubscribe(followerSub);
    }
}
