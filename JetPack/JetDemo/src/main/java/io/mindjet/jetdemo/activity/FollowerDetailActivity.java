package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.Constant;
import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityFollowerDetailBinding;
import io.mindjet.jetimage.loader.ImageLoader;

/**
 * Created by Jet on 3/9/17.
 */

public class FollowerDetailActivity extends BaseDemoActivity {

    private ActivityFollowerDetailBinding binding;

    public static Intent intentFor(Context context) {
        return new Intent(context, FollowerDetailActivity.class);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_follower_detail);
    }

    @Override
    public void initView() {
        String avatar = getIntent().getStringExtra(Constant.INTENT_AVATAR);
        ImageLoader.load(binding.ivAvatar, avatar);
        String name = getIntent().getStringExtra(Constant.INTENT_NAME);
        binding.tvName.setText(name);
    }

    @Override
    public void initData() {

    }

}
