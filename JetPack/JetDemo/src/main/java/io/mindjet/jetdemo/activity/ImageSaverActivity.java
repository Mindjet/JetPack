package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityPhotoSaverBinding;
import io.mindjet.jetutil.file.FileUtil;
import io.mindjet.jetutil.hint.Toaster;

/**
 * Created by Jet on 2/9/17.
 */

public class ImageSaverActivity extends BaseDemoActivity implements View.OnClickListener {

    private ActivityPhotoSaverBinding binding;
    private File appDir;
    private Bitmap bitmap;

    public static Intent intentFor(Context context) {
        return new Intent(context, ImageSaverActivity.class);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_saver);
        try {
            InputStream is = getResources().getAssets().open("icon.png");
            bitmap = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {
        binding.btnGoDirectory.setOnClickListener(this);
        binding.btnSaveDefault.setOnClickListener(this);
        binding.btnSaveLowQuality.setOnClickListener(this);
        binding.btnSaveHighQuality.setOnClickListener(this);
    }

    @Override
    public void initData() {
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
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setDataAndType(Uri.fromFile(appDir), "*/*");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
