package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.viewmodel.SwipeRecyclerDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelCompatActivity;

/**
 * Created by Jet on 3/2/17.
 */

public class SwipeLayoutActivity extends ViewModelCompatActivity<SwipeRecyclerDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, SwipeLayoutActivity.class);
    }

    @Override
    public SwipeRecyclerDemoViewModel giveMeViewModel() {
        return new SwipeRecyclerDemoViewModel();
    }

    @Override
    public void onViewAttached(SwipeRecyclerDemoViewModel viewModel) {

    }
}
