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
    private String url = "https://avatars0.githubusercontent.com/u/17674741?v=3&u=d885cca444cf1881b81076563f49ca4751c675f9&s=400";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_loader);
        initData();
    }

    private void initData() {
        ImageLoader.load(binding.ivOrigin, url);
        ImageLoader.loadWithBlur(binding.ivBlur, url, 20);
        ImageLoader.loadWithRadius(binding.ivRadius, url, 20);
        ImageLoader.loadCircle(binding.ivCircle, url);
    }

}
