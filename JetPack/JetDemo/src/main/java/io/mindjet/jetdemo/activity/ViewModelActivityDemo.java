package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.GithubFollowerListViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;

/**
 * Created by Jet on 2/22/17.
 */

public class ViewModelActivityDemo extends ViewModelActivity<GithubFollowerListViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, ViewModelActivityDemo.class);
    }

    @Override
    public GithubFollowerListViewModel giveMeViewModel() {
        return new GithubFollowerListViewModel("Mindjet");
    }

    @Override
    public void onViewAttached(GithubFollowerListViewModel viewModel) {

    }

}
