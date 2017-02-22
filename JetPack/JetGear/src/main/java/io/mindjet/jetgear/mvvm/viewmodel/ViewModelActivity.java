package io.mindjet.jetgear.mvvm.viewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.mindjet.jetgear.mvvm.base.BaseActivity;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.listener.ViewAttachedListener;

/**
 * Created by Jet on 2/22/17.
 */

public abstract class ViewModelActivity<T extends BaseViewModel> extends BaseActivity implements ViewAttachedListener<T> {

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
