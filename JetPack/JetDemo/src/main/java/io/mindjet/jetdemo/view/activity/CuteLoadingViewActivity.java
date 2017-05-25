package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityLoadingViewBinding;
import io.mindjet.jetdemo.view.activity.base.BaseDemoActivity;

/**
 * Loading View Demo.
 * <p>
 * Created by Mindjet on 5/22/17.
 */

public class CuteLoadingViewActivity extends BaseDemoActivity {

    public static Intent intentFor(Context context) {
        return new Intent(context, CuteLoadingViewActivity.class);
    }

    private ActivityLoadingViewBinding mBinding;

    @Override
    public void beforeInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_loading_view);
        mBinding.setData(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
