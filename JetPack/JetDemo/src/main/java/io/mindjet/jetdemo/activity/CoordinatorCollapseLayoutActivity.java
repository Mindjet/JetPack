package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.CoordinatorCollapseLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelCompatActivity;

/**
 * Created by Jet on 3/2/17.
 */

public class CoordinatorCollapseLayoutActivity extends ViewModelCompatActivity<CoordinatorCollapseLayoutDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, CoordinatorCollapseLayoutActivity.class);
    }

    @Override
    public CoordinatorCollapseLayoutDemoViewModel giveMeViewModel() {
        return new CoordinatorCollapseLayoutDemoViewModel();
    }

    @Override
    public void onViewAttached(CoordinatorCollapseLayoutDemoViewModel viewModel) {

    }

}
