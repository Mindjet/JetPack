package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.widget.LinearLayout;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityBottomSheetBehaviorBinding;
import io.mindjet.jetdemo.view.activity.base.BaseBindingDemoActivity;

/**
 * Custom Behavior demo.
 * <p>
 * Created by Mindjet on 5/25/17.
 */

public class BottomSheetBehaviorActivity extends BaseBindingDemoActivity<ActivityBottomSheetBehaviorBinding> {

    public static Intent intentFor(Context context) {
        return new Intent(context, BottomSheetBehaviorActivity.class);
    }

    private BottomSheetBehavior<LinearLayout> mBehavior;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bottom_sheet_behavior;
    }

    @Override
    public void initView() {
        mBehavior = BottomSheetBehavior.from(mBinding.bottomSheet);
        mBehavior.setHideable(true);        //if hideable is false(default), then exception will be thrown when call setState(STATE_HIDDEN);
    }

    @Override
    public void initData() {

    }

    public void onHidden() {
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    public void onCollapsed() {
        mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    public void onExpended() {
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
}
