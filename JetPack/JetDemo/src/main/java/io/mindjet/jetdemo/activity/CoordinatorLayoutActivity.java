package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.CoordinatorLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CoordinatorLayoutViewModel;

/**
 * Created by Jet on 3/1/17.
 */

public class CoordinatorLayoutActivity extends ViewModelActivity<CoordinatorLayoutViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, CoordinatorLayoutActivity.class);
    }

    @Override
    public CoordinatorLayoutViewModel giveMeViewModel() {
        return new CoordinatorLayoutDemoViewModel();
    }

    @Override
    public void onViewAttached(CoordinatorLayoutViewModel viewModel) {

    }
}
