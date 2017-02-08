package io.mindjet.jetdemo;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;

import io.mindjet.jetdemo.databinding.ActivityImagePickerBinding;
import io.mindjet.jetimage.picker.ImagePicker;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ImagePickerActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityImagePickerBinding binding;

    public static Intent intentFor(Context context) {
        return new Intent(context, ImagePickerActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_picker);
        initListener();
        initData();
    }

    private void initListener() {
        binding.btnTake.setOnClickListener(this);
        binding.btnPick.setOnClickListener(this);
        binding.btnTakeCrop.setOnClickListener(this);
        binding.btnPickCrop.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_take) {
            takePhoto();
        } else if (i == R.id.btn_pick) {
            pickPhoto();
        } else if (i == R.id.btn_take_crop) {
            takeCropPhoto();
        } else {
            pickCropPhoto();
        }
    }

    private void takePhoto() {
        ImagePicker.with(this)
                .requestImage(ImagePicker.TAKE_PHOTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        setImage(file);
                    }
                });
    }

    private void pickPhoto() {
        ImagePicker.with(this)
                .requestImage(ImagePicker.PICK_PHOTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        setImage(file);
                    }
                });
    }

    private void takeCropPhoto() {
        ImagePicker.with(this)
                .requestImage(ImagePicker.TAKE_PHOTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        setImage(file);
                    }
                });
    }

    private void pickCropPhoto() {
        ImagePicker.with(this)
                .requestImage(ImagePicker.TAKE_PHOTO)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        setImage(file);
                    }
                });
    }

    private void setImage(File file) {
        binding.ivPhoto.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
    }

}
