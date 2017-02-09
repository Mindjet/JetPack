package io.mindjet.jetpack;

import android.app.Application;
import android.os.Environment;

import com.orhanobut.logger.Logger;

import java.io.File;

import io.mindjet.jetimage.picker.ImagePicker;

/**
 * Created by Jet on 2/8/17.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
        makeAppDir();
        ImagePicker.setImagePath(Environment.getExternalStorageDirectory() + "/Jetpack");
    }

    private void initLogger() {
        Logger.init().hideThreadInfo().methodCount(1);
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
