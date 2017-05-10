package io.mindjet.jetdemo.viewmodel;

import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.listener.IFollowerListener;
import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.databinding.ItemImageTextBinding;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;
import io.mindjet.jetgear.network.ServiceGen;
import io.mindjet.jetutil.hint.Toaster;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jet on 2/20/17.
 */

public class GithubFollowerListViewModel extends RecyclerViewModel<ItemImageTextBinding> {

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
    protected void initRecyclerView() {
        getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        getRecyclerView().setBackground(getContext().getResources().getDrawable(R.color.rcv_gray_light));
    }

    @Override
    public void onLoadMore() {
        followerSub = service.follower(userName, page, 10)
                .throttleLast(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Follower>>() {
                    @Override
                    public void call(List<Follower> followers) {
                        if (followers.size() == 0) {
//                            getAdapter().finishLoadMore(false); TODO API更新
                        } else {
                            for (Follower follower : followers) {
                                GithubFollowerViewModel vm = new GithubFollowerViewModel(follower).callback(iFollowerListener);
                                getAdapter().add(vm);
                            }
//                            getAdapter().updateAndContinue(); // TODO: 5/10/17 API更新
                            page++;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
//                        getAdapter().finishLoadMore(true);// TODO: 5/10/17 API更新
                        Toaster.toast(getContext(), throwable.getMessage());
                    }
                });
    }

    @Override
    public void onDestroy() {
        followerSub.unsubscribe();
    }
}
