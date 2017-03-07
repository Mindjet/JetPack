package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityTransition2Binding;

/**
 * Created by Jet on 3/7/17.
 */

public class TransitionActivity2 extends BaseDemoActivity {

    private ActivityTransition2Binding binding;

    public static Intent intentFor(Context context) {
        return new Intent(context, TransitionActivity2.class);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transition_2);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        binding.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2077732093,2100397646&fm=21&gp=0.jpg");
    }
}
