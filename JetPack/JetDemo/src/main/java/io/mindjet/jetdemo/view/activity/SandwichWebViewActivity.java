package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.SandwichWebViewDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelCompatActivity;

/**
 * Created by Mindjet on 5/26/17.
 */

public class SandwichWebViewActivity extends ViewModelCompatActivity<SandwichWebViewDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, SandwichWebViewActivity.class);
    }

    @Override
    public void onViewAttached(SandwichWebViewDemoViewModel viewModel) {

    }

    @Override
    public SandwichWebViewDemoViewModel giveMeViewModel() {
        return new SandwichWebViewDemoViewModel();
    }
}
