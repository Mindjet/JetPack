package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityCheckBoxBinding;
import io.mindjet.jetdemo.view.activity.base.BaseDemoActivity;
import io.mindjet.jetutil.hint.Toaster;
import io.mindjet.jetwidget.QCheckBox;

/**
 * Created by Jet on 5/19/17.
 */

public class CuteCheckBoxActivity extends BaseDemoActivity implements QCheckBox.OnCheckedChangedListener {

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
        mBinding.checkbox1.setOnCheckedChangedListener(this);
        mBinding.checkbox2.setOnCheckedChangedListener(this);
        mBinding.checkbox3.setOnCheckedChangedListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onCheckedChanged(QCheckBox checkBox, boolean checked) {
        Toaster.toast(this, checkBox.getId() + ": " + checked);
    }
}
