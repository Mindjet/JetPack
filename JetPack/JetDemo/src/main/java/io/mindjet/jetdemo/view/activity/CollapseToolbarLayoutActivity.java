package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.CollapseToolbarLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelCompatActivity;

/**
 * Created by Jet on 3/2/17.
 */

public class CollapseToolbarLayoutActivity extends ViewModelCompatActivity<CollapseToolbarLayoutDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, CollapseToolbarLayoutActivity.class);
    }

    @Override
    public CollapseToolbarLayoutDemoViewModel giveMeViewModel() {
        return new CollapseToolbarLayoutDemoViewModel();
    }

    @Override
    public void onViewAttached(CollapseToolbarLayoutDemoViewModel viewModel) {

    }

}
