package io.mindjet.jetdemo.viewmodel;

import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.dialog.FollowerDialog;
import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.mvvm.viewmodel.list.SwipeRecyclerViewModel;
import io.mindjet.jetgear.network.ServiceGen;
import io.mindjet.jetgear.reactivex.RxActions;
import io.mindjet.jetgear.reactivex.rxbus.RxBus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Github follower list view model based on {@link SwipeRecyclerViewModel}.
 * <p>
 * Created by Jet on 2/20/17.
 */

public class GithubFollowerListViewModel extends SwipeRecyclerViewModel {

    private String userName;

    private GithubService service;
    private int page = 1;

    private Subscription followerSub;

    private Action1<Follower> onClick;

    public GithubFollowerListViewModel() {
        this("JakeWharton");
    }

    public GithubFollowerListViewModel(String userName) {
        this.userName = userName;
        service = ServiceGen.create(GithubService.class);
    }

    @Override
    protected void afterComponentsBound() {
        getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        getRecyclerView().setBackground(getContext().getResources().getDrawable(R.color.rcv_gray_light));
        changePbColor(R.color.colorPrimary);

        onClick = new Action1<Follower>() {
            @Override
            public void call(Follower follower) {
                new FollowerDialog(getContext(), follower.name).show();
            }
        };
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(RxActions.clearAdapter(getAdapter(), page == 1))
                .observeOn(Schedulers.io())
                .flatMap(RxActions.<Follower>flatMap())
                .map(new Func1<Follower, GithubFollowerViewModel>() {
                    @Override
                    public GithubFollowerViewModel call(Follower follower) {
                        return new GithubFollowerViewModel(follower, onClick);
                    }
                })
                .toList()
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(RxActions.hideRefreshing(getSwipeLayout()))
                .observeOn(AndroidSchedulers.mainThread())
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
