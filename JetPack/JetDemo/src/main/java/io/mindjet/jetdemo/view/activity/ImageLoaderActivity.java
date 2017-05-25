package io.mindjet.jetdemo.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityImageLoaderBinding;
import io.mindjet.jetdemo.view.activity.base.BaseDemoActivity;
import io.mindjet.jetimage.loader.ImageLoader;

/**
 * Created by Jet on 2/10/17.
 */

public class ImageLoaderActivity extends BaseDemoActivity {

    public static Intent intentFor(Context context) {
        return new Intent(context, ImageLoaderActivity.class);
    }

    private ActivityImageLoaderBinding binding;
    private String url = "https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg";

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_loader);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ImageLoader.load(binding.ivOrigin, url, 0);
        ImageLoader.loadWithBlur(binding.ivBlur, url, 20);
        ImageLoader.loadWithRadiusPlaceHolder(binding.ivRadius, url, 20, 0);
        ImageLoader.loadCircleWithPlaceHolder(binding.ivCircle, url, 0);
    }

}
