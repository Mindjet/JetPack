package io.mindjet.jetpack;

import android.app.Application;
import android.os.Environment;

import io.mindjet.jetimage.picker.ImagePicker;

/**
 * Created by Jet on 2/8/17.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePicker.setImagePath(Environment.getExternalStorageDirectory() + "/Jetpack");
    }

}
