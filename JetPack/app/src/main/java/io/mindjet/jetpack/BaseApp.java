package io.mindjet.jetpack;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import io.mindjet.jetimage.picker.ImagePicker;

/**
 * Created by Jet on 2/8/17.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        makeAppDir();
        ImagePicker.setImagePath(Environment.getExternalStorageDirectory() + "/Jetpack");
    }

    /**
     * Make a directory for this application.
     */
    private void makeAppDir() {
        File appDir = new File(Environment.getExternalStorageDirectory() + "/Jetpack");
        if (!appDir.exists())
            appDir.mkdir();
    }

}
