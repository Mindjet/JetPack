package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import io.mindjet.jetdemo.Constant;
import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityTransition2Binding;
import io.mindjet.jetutil.view.AnimUtil;

/**
 * Created by Jet on 3/7/17.
 */

public class TransitionActivity2 extends BaseDemoActivity {

    private ActivityTransition2Binding binding;
    private String imageUrl;

    public static Intent intentFor(Context context) {
        return new Intent(context, TransitionActivity2.class);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transition_2);
        binding.getRoot().setVisibility(View.INVISIBLE);
        binding.getRoot().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                binding.getRoot().removeOnLayoutChangeListener(this);
                AnimUtil.revealCenter(v, 1000);
            }
        });
    }

    @Override
    public void initView() {
        imageUrl = getIntent().getStringExtra(Constant.INTENT_AVATAR);
    }

    @Override
    public void initData() {
        binding.setImageUrl(imageUrl);
    }

    @Override
    public void onBackPressed() {
        AnimUtil.concealCenter(binding.getRoot(), 500);
        finish();
    }
}
