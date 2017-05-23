package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityCornerLinearLayoutBinding;

/**
 * CornerLinearLayout demo.
 * <p>
 * Created by Mindjet on 5/23/17.
 */

public class CornerLinearLayoutActivity extends BaseDemoActivity {

    public static Intent intentFor(Context context) {
        return new Intent(context, CornerLinearLayoutActivity.class);
    }

    private ActivityCornerLinearLayoutBinding mBinding;

    @Override
    public void beforeInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_corner_linear_layout);
        mBinding.setData(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
