package io.mindjet.jetimage.picker;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jet on 2/7/17.
 */

public class HiddenActivity extends Activity {

    public static final String SOURCE = "source";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent();
    }

    private void handleIntent() {
        if (!checkPermission()) {
            return;
        }
        @ImagePicker.Source int sourceType = getIntent().getIntExtra(SOURCE, 0);
        switch (sourceType) {
            case ImagePicker.TAKE_PHOTO:
                handleTakePhoto();
                break;
            case ImagePicker.PICK_PHOTO:
                break;
            case ImagePicker.TAKE_CROP_PHOTO:
                break;
            case ImagePicker.PICK_CROP_PHOTO:
                break;
        }
    }

    private void handleTakePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, createUri());
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, ImagePicker.TAKE_PHOTO);
    }

    private void handlePickPhoto() {

    }

    private void handleTakeCropPhoto() {

    }

    private void handlePickCropPhoto() {

    }

    //TODO
    private boolean checkPermission() {
        return true;
    }

    private Uri createUri() {
        ContentResolver contentResolver = getContentResolver();
        ContentValues cv = new ContentValues();
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        cv.put(MediaStore.Images.Media.TITLE, timeStamp);
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ImagePicker.TAKE_PHOTO:

                    break;
            }
        }
    }
}
