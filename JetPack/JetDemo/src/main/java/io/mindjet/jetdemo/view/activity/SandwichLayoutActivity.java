package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.SandwichLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelCompatActivity;

/**
 * Sandwich view demo.
 * Created by Mindjet on 5/26/17.
 */

public class SandwichLayoutActivity extends ViewModelCompatActivity<SandwichLayoutDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, SandwichLayoutActivity.class);
    }

    @Override
    public void onViewAttached(SandwichLayoutDemoViewModel viewModel) {

    }

    @Override
    public SandwichLayoutDemoViewModel giveMeViewModel() {
        return new SandwichLayoutDemoViewModel();
    }

}
