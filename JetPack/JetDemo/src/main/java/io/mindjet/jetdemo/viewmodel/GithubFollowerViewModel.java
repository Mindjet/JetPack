package io.mindjet.jetdemo.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ItemGithubFollowerBinding;
import io.mindjet.jetdemo.model.Follower;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetutil.hint.Toaster;

/**
 * Created by Jet on 2/20/17.
 */

public class GithubFollowerViewModel extends BaseViewModel<ViewInterface<ItemGithubFollowerBinding>> {

    public ObservableField<String> avatarUrl;
    public ObservableField<String> name;

    public GithubFollowerViewModel(Follower follower) {
        avatarUrl = new ObservableField<>(follower.avatarUrl);
        name = new ObservableField<>(follower.name);
    }

    @Override
    public void onViewAttached(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_github_follower;
    }

    public View.OnClickListener getClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toaster.toast(getContext(), name.get());
            }
        };
    }

}
