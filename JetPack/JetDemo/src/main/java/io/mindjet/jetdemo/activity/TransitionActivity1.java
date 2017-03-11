package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.MotionEvent;
import android.view.View;

import io.mindjet.jetdemo.Constant;
import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityTransition1Binding;
import io.mindjet.jetutil.manager.TransManager;

/**
 * Created by Jet on 3/7/17.
 */

public class TransitionActivity1 extends BaseDemoActivity {

    private ActivityTransition1Binding binding;
    private String imageUrl = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2077732093,2100397646&fm=21&gp=0.jpg";

    public static Intent intentFor(Context context) {
        return new Intent(context, TransitionActivity1.class);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transition_1);
    }

    @Override
    public void initView() {
        binding.target1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TransitionActivity2.intentFor(TransitionActivity1.this);
                intent.putExtra(Constant.INTENT_AVATAR, imageUrl);
                TransManager.get()
                        .with(TransitionActivity1.this)
                        .pair(binding.image1, R.string.transition_name_image)
                        .pair(binding.text1, R.string.transition_name_text)
                        .startActivity(intent);
            }
        });
        binding.target2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = TransitionActivity2.intentFor(TransitionActivity1.this);
                    intent.putExtra(Constant.INTENT_AVATAR, imageUrl);
                    intent.putExtra(Constant.INTENT_CENTERX, event.getRawX());
                    intent.putExtra(Constant.INTENT_CENTERY, event.getRawY());
                    startActivity(intent);
                }
                return true;
            }
        });
    }

    @Override
    public void initData() {
        binding.setImageUrl(imageUrl);
    }

}
