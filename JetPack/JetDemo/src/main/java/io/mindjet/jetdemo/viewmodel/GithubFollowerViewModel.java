package io.mindjet.jetdemo.viewmodel;

import android.view.View;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ItemGithubFollowerBinding;
import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.AdapterInterface;
import rx.functions.Action1;

/**
 * Created by Jet on 2/20/17.
 */

public class GithubFollowerViewModel extends BaseViewModel<AdapterInterface<ItemGithubFollowerBinding>> {

    private Follower follower;

    private Action1<Follower> onClick;

    public GithubFollowerViewModel(Follower follower, Action1<Follower> onClick) {
        this.follower = follower;
        this.onClick = onClick;
    }

    public Follower getFollower() {
        return follower;
    }

    @Override
    public void onViewAttached(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_github_follower;
    }

    public void onClick() {
        if (onClick != null) onClick.call(follower);
    }

}
