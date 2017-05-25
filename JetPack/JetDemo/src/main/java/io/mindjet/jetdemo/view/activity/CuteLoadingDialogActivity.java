package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityCuteLoadingDialogBinding;
import io.mindjet.jetdemo.view.activity.base.BaseDemoActivity;
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

    public void onDefaultDialog() {
        new QLoadingDialog.Builder(this)
                .build()
                .show();
    }

    public void onColorDialog() {
        new QLoadingDialog.Builder(this)
                .backgroundColorRes(R.color.purple)
                .contentColorRes(R.color.white)
                .loadingViewColorRes(R.color.white)
                .loadingBallsEclipsed(true)
                .build()
                .show();
    }

    public void onCornerDialog() {
        new QLoadingDialog.Builder(this)
                .backgroundColorRes(R.color.purple)
                .contentColorRes(R.color.white)
                .loadingViewColorRes(R.color.white)
                .cornerRadius(R.dimen.common_gap_medium)
                .loadingBallsEclipsed(true)
                .build()
                .show();
    }

}
