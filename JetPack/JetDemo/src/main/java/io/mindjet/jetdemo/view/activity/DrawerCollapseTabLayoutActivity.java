package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.DrawerCollapseTabLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;

/**
 * Created by Jet on 3/9/17.
 */

public class DrawerCollapseTabLayoutActivity extends ViewModelActivity<DrawerCollapseTabLayoutDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, DrawerCollapseTabLayoutActivity.class);
    }

    @Override
    public void onViewAttached(DrawerCollapseTabLayoutDemoViewModel viewModel) {

    }

    @Override
    public DrawerCollapseTabLayoutDemoViewModel giveMeViewModel() {
        return new DrawerCollapseTabLayoutDemoViewModel();
    }
}
