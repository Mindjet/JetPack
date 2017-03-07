package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityTransition1Binding;

/**
 * Created by Jet on 3/7/17.
 */

public class TransitionActivity1 extends BaseDemoActivity {

    private ActivityTransition1Binding binding;

    public static Intent intentFor(Context context) {
        return new Intent(context, TransitionActivity1.class);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transition_1);
    }

    @Override
    public void initView() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        TransitionActivity1.this, binding.image, getResources().getString(R.string.transition_name_image)
                );
                ActivityCompat.startActivity(TransitionActivity1.this, TransitionActivity2.intentFor(TransitionActivity1.this), options.toBundle());
            }
        });
    }

    @Override
    public void initData() {
        binding.setImageUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2077732093,2100397646&fm=21&gp=0.jpg");
    }

}
