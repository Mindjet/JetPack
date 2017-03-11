package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.ReaderDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ReaderDemoActivity extends ViewModelActivity<ReaderDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, ReaderDemoActivity.class);
    }

    @Override
    public ReaderDemoViewModel giveMeViewModel() {
        return new ReaderDemoViewModel();
    }

    @Override
    public void onViewAttached(ReaderDemoViewModel viewModel) {

    }
}
