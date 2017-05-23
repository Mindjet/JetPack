package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.Gravity;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityCuteLoadingDialogBinding;
import io.mindjet.jetwidget.QLoadingDialog;

/**
 * Cute loading dialog demo.
 * <p>
 * Created by Mindjet on 5/23/17.
 */

public class CuteLoadingDialogActivity extends BaseDemoActivity {

    public static Intent intentFor(Context context) {
        return new Intent(context, CuteLoadingDialogActivity.class);
    }

    private ActivityCuteLoadingDialogBinding mBinding;

    @Override
    public void beforeInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cute_loading_dialog);
        mBinding.setData(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    public void onDefault() {
        new QLoadingDialog.Builder(this)
                .content(R.string.app_name)
                .backgroundColorRes(R.color.purple)
                .loadingViewColorRes(R.color.white)
                .contentColorRes(R.color.white)
                .textSize(R.dimen.common_text_size)
                .gravity(Gravity.CENTER)
                .cancelable(true)
                .build()
                .show();
    }
}
