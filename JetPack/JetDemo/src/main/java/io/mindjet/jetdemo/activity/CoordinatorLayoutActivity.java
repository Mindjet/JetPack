package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.CoordinatorTabLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CoordinatorTabLayoutViewModel;

/**
 * Created by Jet on 3/1/17.
 */

public class CoordinatorLayoutActivity extends ViewModelActivity<CoordinatorTabLayoutViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, CoordinatorLayoutActivity.class);
    }

    @Override
    public CoordinatorTabLayoutViewModel giveMeViewModel() {
        return new CoordinatorTabLayoutDemoViewModel();
    }

    @Override
    public void onViewAttached(CoordinatorTabLayoutViewModel viewModel) {

    }
}
