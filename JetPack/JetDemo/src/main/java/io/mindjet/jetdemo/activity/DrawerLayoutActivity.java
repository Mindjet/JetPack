package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.DrawerLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;

/**
 * Created by Jet on 2/24/17.
 */

public class DrawerLayoutActivity extends ViewModelActivity<DrawerLayoutDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, DrawerLayoutActivity.class);
    }

    @Override
    public DrawerLayoutDemoViewModel giveMeViewModel() {
        return new DrawerLayoutDemoViewModel();
    }

    @Override
    public void onViewAttached(DrawerLayoutDemoViewModel viewModel) {

    }
}
