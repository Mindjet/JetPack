package io.mindjet.jetgear.mvvm;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ActivityTestBinding;
import io.mindjet.jetgear.mvvm.adapter.ListAdapter;
import io.mindjet.jetgear.mvvm.base.BaseActivity;
import io.mindjet.jetgear.mvvm.viewmodel.ImageTextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.TextViewModel;

/**
 * Created by Mindjet on 2017/2/15.
 */

public class TestActivity extends BaseActivity {

    private ActivityTestBinding binding;
    private ListAdapter listAdapter;

    public static Intent intentFor(Context context) {
        return new Intent(context, TestActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listAdapter = new ListAdapter<>(this);
        binding.recyclerView.setAdapter(listAdapter);
    }

    private void initData() {
        listAdapter.add(new TextViewModel("asddas"));
        listAdapter.add(new ImageTextViewModel());
        listAdapter.add(new ImageTextViewModel());
        listAdapter.add(new TextViewModel("asddas"));
        listAdapter.add(new ImageTextViewModel());
    }

}
