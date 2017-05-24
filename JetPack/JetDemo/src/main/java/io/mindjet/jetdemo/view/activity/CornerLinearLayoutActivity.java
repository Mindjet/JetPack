package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityCornerLinearLayoutBinding;
import io.mindjet.jetutil.view.ColorUtil;
import io.mindjet.jetwidget.CornerLinearLayout;

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
    private CornerLinearLayout mCornerLinearLayout;

    private float mCurrentRadius;
    private int mCurrentColor;

    public ObservableField<String> statement = new ObservableField<>("");

    @Override
    public void beforeInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_corner_linear_layout);
        mBinding.setData(this);
    }

    public void onIncreaseRadius() {
        if (mCurrentRadius >= mCornerLinearLayout.getHeight() / 2) return;
        mCurrentRadius += 10;
        mCornerLinearLayout.setCornerRadius(mCurrentRadius);
        updateStatement();
    }

    public void onDecreaseRadius() {
        if (mCurrentRadius <= 20) return;
        mCurrentRadius -= 10;
        mCornerLinearLayout.setCornerRadius(mCurrentRadius - 10);
        updateStatement();
    }

    public void onChangeColor() {
        mCurrentColor = ColorUtil.randomColor();
        mCornerLinearLayout.setBackgroundColor(mCurrentColor);
        updateStatement();
    }

    private void updateStatement() {
        statement.set("radius: " + mCurrentRadius + "px  color: #" + Integer.toHexString(mCurrentColor));
    }

    @Override
    public void initView() {
        mCornerLinearLayout = mBinding.cornerLinearLayout;
    }

    @Override
    public void initData() {
        mCurrentRadius = mCornerLinearLayout.getCornerRadius();
        mCurrentColor = ColorUtil.getBackgroundColor(mCornerLinearLayout);
        updateStatement();
    }

}
