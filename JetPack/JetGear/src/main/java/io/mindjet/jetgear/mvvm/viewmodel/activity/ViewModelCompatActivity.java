package io.mindjet.jetgear.mvvm.viewmodel.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.mindjet.jetgear.mvvm.base.BaseCompatActivity;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.listener.ViewAttachedListener;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;

/**
 * Created by Jet on 2/28/17.
 */

public abstract class ViewModelCompatActivity<T extends BaseViewModel> extends BaseCompatActivity implements ViewAttachedListener<T> {

    private T viewModel;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = giveMeViewModel();
        viewModel.setViewAttachedListener(this);
        ViewModelBinder.bind(this, viewModel);
    }

    public abstract T giveMeViewModel();

    @Override
    protected void onResume() {
        super.onResume();
        if (viewModel != null)
            viewModel.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (viewModel != null)
            viewModel.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel != null)
            viewModel.onDestroy();
    }
}