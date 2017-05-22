package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.CollapseTabLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CollapseTabLayoutViewModel;

/**
 * Created by Jet on 3/1/17.
 */

public class CollapseTabLayoutActivity extends ViewModelActivity<CollapseTabLayoutViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, CollapseTabLayoutActivity.class);
    }

    @Override
    public CollapseTabLayoutViewModel giveMeViewModel() {
        return new CollapseTabLayoutDemoViewModel();
    }

    @Override
    public void onViewAttached(CollapseTabLayoutViewModel viewModel) {

    }
}
