package io.mindjet.jetdemo.viewmodel;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.databinding.ItemImageTextBinding;
import io.mindjet.jetgear.mvvm.viewmodel.ImageTextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.RecyclerViewModel;
import io.mindjet.jetgear.network.ServiceGen;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jet on 2/20/17.
 */

public class GithubFollowersViewModel extends RecyclerViewModel<ItemImageTextBinding> {

    private GithubService service;
    private int page = 1;

    public GithubFollowersViewModel(Context context) {
        super(context);
        service = ServiceGen.create(GithubService.class);
    }

    @Override
    public void onLoadMore() {
        service.follower("zhougch5", page, 3)
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
                                ImageTextViewModel vm = new ImageTextViewModel();
                                vm.setImageUrl(follower.avatarUrl);
                                vm.setTitle(follower.name);
                                vm.setContent(follower.htmlUrl);
                                getAdapter().add(vm);
                            }
                            getAdapter().updateAndContinue();
                            page++;
                        }
                    }
                });
    }
}
