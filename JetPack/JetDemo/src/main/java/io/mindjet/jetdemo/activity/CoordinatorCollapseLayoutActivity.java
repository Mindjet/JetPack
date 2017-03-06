package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.mindjet.jetdemo.viewmodel.CoordinatorCollapseLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelCompatActivity;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CoordinatorCollapseLayoutViewModel;

/**
 * Created by Jet on 3/2/17.
 */

public class CoordinatorCollapseLayoutActivity extends ViewModelCompatActivity<CoordinatorCollapseLayoutViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, CoordinatorCollapseLayoutActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public CoordinatorCollapseLayoutViewModel giveMeViewModel() {
        return new CoordinatorCollapseLayoutDemoViewModel();
    }

    @Override
    public void onViewAttached(CoordinatorCollapseLayoutViewModel viewModel) {

    }

}
