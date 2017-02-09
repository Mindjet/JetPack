package io.mindjet.jetdemo;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import io.mindjet.jetdemo.databinding.ActivitySavePhotoBinding;
import io.mindjet.jetutil.file.FileUtil;
import io.mindjet.jetutil.file.RxFile;
import io.mindjet.jetutil.toast.Toaster;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Jet on 2/9/17.
 */

public class SavePhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySavePhotoBinding binding;
    private File appDir;
    private Bitmap bitmap;

    public static Intent intentFor(Context context) {
        return new Intent(context, SavePhotoActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_save_photo);
        beforeInitView();
        initListener();
        initData();
    }

    private void beforeInitView() {
        try {
            InputStream is = getResources().getAssets().open("icon.png");
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        binding.btnGoDirectory.setOnClickListener(this);
        binding.btnSaveDefault.setOnClickListener(this);
        binding.btnSaveLowQuality.setOnClickListener(this);
        binding.btnSaveHighQuality.setOnClickListener(this);
    }

    private void initData() {
        appDir = new File(Environment.getExternalStorageDirectory() + "/Jetpack");
    }

    @Override
    public void onClick(View v) {
        try {
            String uuid = UUID.randomUUID().toString();
            int i = v.getId();
            if (i == R.id.btn_go_directory) {
                gotoDir();
            } else if (i == R.id.btn_save_default) {
                FileUtil.savePhoto(bitmap, appDir, "default-" + uuid);
                Toaster.toast(this, "Photo is saved at " + appDir + "/default-" + uuid + "\n" + "Click the 1st button to check it out.");
            } else if (i == R.id.btn_save_low_quality) {
                FileUtil.savePhoto(bitmap, appDir, "low-" + uuid, FileUtil.EXT_JPG, FileUtil.QUALITY_LOW);
                Toaster.toast(this, "Photo is saved at " + appDir + "/low-" + uuid + "\n" + "Click the 1st button to check it out.");
            } else if (i == R.id.btn_save_high_quality) {
                FileUtil.savePhoto(bitmap, appDir, "high-" + uuid, FileUtil.EXT_PNG, FileUtil.QUALITY_HIGH);
                Toaster.toast(this, "Photo is saved at " + appDir + "/high-" + uuid + "\n" + "Click the 1st button to check it out.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gotoDir() {
        RxFile.get()
                .saveBitmap(bitmap, appDir, "rxasdasd")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Logger.e(s + Looper.myLooper().toString());
                    }
                });

    }

}
