package io.mindjet.jetdemo.viewmodel;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.databinding.ItemImageTextBinding;
import io.mindjet.jetgear.mvvm.viewmodel.RecyclerViewModel;
import io.mindjet.jetgear.network.ServiceGen;
import io.mindjet.jetutil.toast.Toaster;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jet on 2/20/17.
 */

public class GithubFollowerListViewModel extends RecyclerViewModel<ItemImageTextBinding> {

    private GithubService service;
    private int page = 1;

    public GithubFollowerListViewModel(Context context) {
        super(context);
        service = ServiceGen.create(GithubService.class);
    }

    @Override
    protected void initRecyclerView() {
        getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        getRecyclerView().setBackground(getContext().getResources().getDrawable(R.color.rcv_gray_light));
    }

    @Override
    public void onLoadMore() {
        service.follower("JakeWharton", page, 10)
                .throttleLast(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Follower>>() {
                    @Override
                    public void call(List<Follower> followers) {
                        if (followers.size() == 0) {
                            getAdapter().finishLoadMore(false);
                        } else {
                            for (Follower follower : followers) {
                                GithubFollowerViewModel vm = new GithubFollowerViewModel(follower);
                                getAdapter().add(vm);
                            }
                            getAdapter().updateAndContinue();
                            page++;
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        getAdapter().finishLoadMore(true);
                        Toaster.toast(getContext(), throwable.getMessage());
                    }
                });
    }

}
