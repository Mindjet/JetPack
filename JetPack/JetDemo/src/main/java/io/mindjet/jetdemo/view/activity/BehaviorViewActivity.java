package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.view.View;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityBehaviorViewBinding;
import io.mindjet.jetdemo.view.activity.base.BaseBindingDemoActivity;

/**
 * Custom Behavior demo.
 * <p>
 * Created by Mindjet on 5/25/17.
 */

public class BehaviorViewActivity extends BaseBindingDemoActivity<ActivityBehaviorViewBinding> {

    public static Intent intentFor(Context context) {
        return new Intent(context, BehaviorViewActivity.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_behavior_view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    public void onDependentClick(View view) {
        ViewCompat.offsetTopAndBottom(view, 10);
    }
}
