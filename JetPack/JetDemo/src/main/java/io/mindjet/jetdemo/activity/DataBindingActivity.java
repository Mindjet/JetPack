package io.mindjet.jetdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityDataBindingBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.RecyclerViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;

/**
 * Created by Jet on 2/16/17.
 */

public class DataBindingActivity extends BaseDemoActivity implements ActivityInterface<ActivityDataBindingBinding> {

    private ActivityDataBindingBinding binding;

    public static Intent intentFor(Context context) {
        return new Intent(context, DataBindingActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
    }

    @Override
    public void initView() {
        RecyclerViewModel recyclerViewModel = new RecyclerViewModel(this);
        ViewModelBinder.bind(binding.llyContainer, recyclerViewModel);
    }

    @Override
    public void initData() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public ActivityDataBindingBinding getBinding() {
        return binding;
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
