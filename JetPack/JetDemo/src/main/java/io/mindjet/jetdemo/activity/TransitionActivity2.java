package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.Constant;
import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityTransition2Binding;
import io.mindjet.jetutil.anim.RevealUtil;

/**
 * Created by Jet on 3/7/17.
 */

public class TransitionActivity2 extends BaseDemoActivity {

    private ActivityTransition2Binding binding;
    private String imageUrl;

    private int centerX;
    private int centerY;

    public static Intent intentFor(Context context) {
        return new Intent(context, TransitionActivity2.class);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transition_2);
        if (getIntent().getFloatExtra(Constant.INTENT_CENTERX, 0) != 0) {
            centerX = (int) getIntent().getFloatExtra(Constant.INTENT_CENTERX, 0);
            centerY = (int) getIntent().getFloatExtra(Constant.INTENT_CENTERY, 0);
            RevealUtil.revealActivity(this, 500, centerX, centerY);
        }
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
        if (getIntent().getFloatExtra(Constant.INTENT_CENTERX, 0) != 0)
            RevealUtil.concealActivity(this, 500, centerX, centerY);
        else super.onBackPressed();
    }
}
