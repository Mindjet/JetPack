package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityCheckBoxBinding;

/**
 * Created by Jet on 5/19/17.
 */

public class CuteCheckBoxActivity extends BaseDemoActivity {

    public static Intent intentFor(Context context) {
        return new Intent(context, CuteCheckBoxActivity.class);
    }

    private ActivityCheckBoxBinding mBinding;

    @Override
    public void beforeInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_box);
        mBinding.setData(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

}
