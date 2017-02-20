package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityImageLoaderBinding;
import io.mindjet.jetimage.loader.ImageLoader;

/**
 * Created by Jet on 2/10/17.
 */

public class ImageLoaderActivity extends AppCompatActivity {

    public static Intent intentFor(Context context) {
        return new Intent(context, ImageLoaderActivity.class);
    }

    private ActivityImageLoaderBinding binding;
    private String url = "https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_loader);
        initData();
    }

    private void initData() {
        ImageLoader.load(binding.ivOrigin, url, 0);
        ImageLoader.loadWithBlur(binding.ivBlur, url, 20);
        ImageLoader.loadWithRadiusPlaceHolder(binding.ivRadius, url, 20, 0);
        ImageLoader.loadCircleWithPlaceHolder(binding.ivCircle, url, 0);
    }

}
