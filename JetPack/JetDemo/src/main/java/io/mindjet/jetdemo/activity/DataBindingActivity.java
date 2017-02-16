package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityDataBindingBinding;
import io.mindjet.jetgear.mvvm.adapter.ListAdapter;
import io.mindjet.jetgear.mvvm.adapter.LoadMoreAdapter;
import io.mindjet.jetgear.mvvm.viewmodel.ImageTextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.TextViewModel;

/**
 * Created by Jet on 2/16/17.
 */

public class DataBindingActivity extends BaseDemoActivity {

    private ActivityDataBindingBinding binding;
    private LoadMoreAdapter adapter;

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
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setBackground(getResources().getDrawable(R.color.rcv_gray_light));
        adapter = new LoadMoreAdapter(this);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }
}
