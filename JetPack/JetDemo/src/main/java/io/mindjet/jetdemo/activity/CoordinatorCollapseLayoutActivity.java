package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CoordinatorCollapseLayoutViewModel;

/**
 * Created by Jet on 3/2/17.
 */

public class CoordinatorCollapseLayoutActivity extends ViewModelActivity<CoordinatorCollapseLayoutViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, CoordinatorCollapseLayoutActivity.class);
    }

    @Override
    public CoordinatorCollapseLayoutViewModel giveMeViewModel() {
        return new CoordinatorCollapseLayoutViewModel();
    }

    @Override
    public void onViewAttached(CoordinatorCollapseLayoutViewModel viewModel) {

    }
}
