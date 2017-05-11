package io.mindjet.jetdemo.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.DialogFollowerBinding;
import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.base.BaseViewModelDialog;
import io.mindjet.jetgear.network.ServiceGen;
import io.mindjet.jetgear.reactivex.RxActions;
import io.mindjet.jetgear.reactivex.rxbus.RxBus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Jet on 3/11/17.
 */

public class FollowerDialog extends BaseViewModelDialog<DialogFollowerBinding> {

    private String userName;

    private Subscription userDetailSub;
    private Follower mFollower;

    public FollowerDialog(@NonNull Context context) {
        super(context);
    }

    public FollowerDialog userName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getAvatar() {
        return mFollower == null ? "" : mFollower.avatarUrl;
    }

    public String getEmail() {
        return mFollower == null ? "" : mFollower.email;
    }

    public String getLocation() {
        return mFollower == null ? "" : mFollower.location;
    }

    public String getPublicRepos() {
        return mFollower == null ? "" : mFollower.publicRepos;
    }

    public String getFollowers() {
        return mFollower == null ? "" : mFollower.followers;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_follower;
    }

    @Override
    protected void afterViewAttached(DialogFollowerBinding binding) {
        GithubService service = ServiceGen.create(GithubService.class);
        userDetailSub = service.getUserDetail(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Follower>() {
                    @Override
                    public void call(Follower follower) {
                        mFollower = follower;
                    }
                }, RxActions.onError());
    }

    @Override
    public void onDetachedFromWindow() {
        RxBus.unSubscribe(userDetailSub);
    }
}
