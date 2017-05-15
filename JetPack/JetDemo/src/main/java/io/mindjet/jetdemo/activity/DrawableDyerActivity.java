package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.widget.ImageView;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityDrawableDyerBinding;
import io.mindjet.jetutil.drawable.DrawableDyer;

/**
 * Created by Jet on 5/11/17.
 */

public class DrawableDyerActivity extends BaseDemoActivity {

    public static Intent intentFor(Context context){
        return new Intent(context, DrawableDyerActivity.class);
    }

    private ActivityDrawableDyerBinding mBinding;
    private ImageView target;

    @Override
    public void beforeInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_drawable_dyer);
        mBinding.setData(this);
    }

    @Override
    public void initView() {
        target = mBinding.ivTarget;
    }

    @Override
    public void initData() {

    }

    public void onBlue() {
        target.setImageDrawable(DrawableDyer.dye(target.getDrawable(), Color.BLUE));
        target.invalidate();
    }

    public void onRed() {
        target.setImageDrawable(DrawableDyer.dye(target.getDrawable(), Color.RED));
        target.invalidate();
    }

    public void onGreen() {
        target.setImageDrawable(DrawableDyer.dye(target.getDrawable(), Color.GREEN));
        target.invalidate();
    }

    public void onRandomColor() {
        target.setImageDrawable(DrawableDyer.dyeRandomColor(target.getDrawable()));
        target.invalidate();
    }

}
