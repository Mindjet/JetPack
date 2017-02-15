package io.mindjet.jetpack;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.mindjet.jetdemo.activity.ImageLoaderActivity;
import io.mindjet.jetdemo.activity.ImagePickerActivity;
import io.mindjet.jetdemo.activity.ImageSaverActivity;
import io.mindjet.jetgear.mvvm.TestActivity;
import io.mindjet.jetpack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initListener();
    }

    private void initListener() {
        binding.btnImagePicker.setOnClickListener(this);
        binding.btnImageSaver.setOnClickListener(this);
        binding.btnImageLoader.setOnClickListener(this);
        binding.btnDatabinding.setOnClickListener(this);
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
            case R.id.btn_databinding:
                startActivity(TestActivity.intentFor(this));
                break;
        }
    }
}
