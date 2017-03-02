package io.mindjet.jetpack;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.mindjet.jetdemo.activity.BannerActivity;
import io.mindjet.jetdemo.activity.CoordinatorLayoutActivity;
import io.mindjet.jetdemo.activity.DrawerLayoutActivity;
import io.mindjet.jetdemo.activity.ImageLoaderActivity;
import io.mindjet.jetdemo.activity.ImagePickerActivity;
import io.mindjet.jetdemo.activity.ImageSaverActivity;
import io.mindjet.jetdemo.activity.NativeDrawerLayoutActivity;
import io.mindjet.jetdemo.activity.SwipeLayoutActivity;
import io.mindjet.jetdemo.activity.ViewModelActivityDemo;
import io.mindjet.jetpack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
    }

    private void initData() {
        binding.setData(this);
//        binding.btnImagePicker.setOnClickListener(this);
//        binding.btnImageSaver.setOnClickListener(this);
//        binding.btnImageLoader.setOnClickListener(this);
//        binding.btnViewmodelActivity.setOnClickListener(this);
//        binding.btnBanner.setOnClickListener(this);
//        binding.btnDrawerLayout.setOnClickListener(this);
//        binding.btnNativeDrawerLayout.setOnClickListener(this);
//        binding.btnCoordinatorLayout.setOnClickListener(this);
    }

    public View.OnClickListener getClickListener() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_image_picker:
                startActivity(ImagePickerActivity.intentFor(this));
                break;
            case R.id.btn_image_saver:
                startActivity(ImageSaverActivity.intentFor(this));
                break;
            case R.id.btn_image_loader:
                startActivity(ImageLoaderActivity.intentFor(this));
                break;
            case R.id.btn_viewmodel_activity:
                startActivity(ViewModelActivityDemo.intentFor(this));
                break;
            case R.id.btn_banner:
                startActivity(BannerActivity.intentFor(this));
                break;
            case R.id.btn_drawer_layout:
                startActivity(DrawerLayoutActivity.intentFor(this));
                break;
            case R.id.btn_native_drawer_layout:
                startActivity(NativeDrawerLayoutActivity.intentFor(this));
                break;
            case R.id.btn_coordinator_layout:
                startActivity(CoordinatorLayoutActivity.intentFor(this));
                break;
            case R.id.btn_swipe_view:
                startActivity(SwipeLayoutActivity.intentFor(this));
                break;
        }
    }
}
