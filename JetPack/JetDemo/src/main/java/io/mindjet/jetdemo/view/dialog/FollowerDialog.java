package io.mindjet.jetdemo.view.dialog;

import android.content.Context;
import android.databinding.ObservableField;
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

    public ObservableField<String> avatar = new ObservableField<>("");
    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> location = new ObservableField<>("");
    public ObservableField<String> publicRepos = new ObservableField<>("");
    public ObservableField<String> followers = new ObservableField<>("");

    public FollowerDialog(@NonNull Context context, String userName) {
        super(context);
        this.userName = userName;
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
                        avatar.set(follower.avatarUrl);
                        email.set(follower.email);
                        location.set(follower.location);
                        publicRepos.set(follower.publicRepos);
                        followers.set(follower.followers);
                    }
                }, RxActions.onError());
    }

    @Override
    public void onDetachedFromWindow() {
        RxBus.unSubscribe(userDetailSub);
    }

}
