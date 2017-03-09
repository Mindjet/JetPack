package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.DrawerCoordinatorLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;

/**
 * Created by Jet on 3/9/17.
 */

public class DrawerCoordinatorLayoutActivity extends ViewModelActivity<DrawerCoordinatorLayoutDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, DrawerCoordinatorLayoutActivity.class);
    }

    @Override
    public void onViewAttached(DrawerCoordinatorLayoutDemoViewModel viewModel) {

    }

    @Override
    public DrawerCoordinatorLayoutDemoViewModel giveMeViewModel() {
        return new DrawerCoordinatorLayoutDemoViewModel();
    }
}
