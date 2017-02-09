package io.mindjet.jetutil.file;

import android.graphics.Bitmap;
import android.os.Looper;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;

import rx.schedulers.Schedulers;
import rx.subjects.ReplaySubject;

/**
 * Created by Jet on 2/9/17.
 */

public class RxFile {

    private static RxFile rxFile;

    public static RxFile get() {
        if (rxFile == null) {
            synchronized (RxFile.class) {
                if (rxFile == null) {
                    rxFile = new RxFile();
                }
            }
        }
        return rxFile;
    }

    private ReplaySubject<String> bitmapSubject;

    public ReplaySubject<String> saveBitmap(Bitmap bitmap, File folder, String name) {
        bitmapSubject = ReplaySubject.create();
        bitmapSubject.subscribeOn(Schedulers.io());
        try {
            String path = FileUtil.savePhoto(bitmap, folder, name);
            onSuccess(path);
            Logger.e(Looper.myLooper().toString());
        } catch (IOException e) {
            e.printStackTrace();
            onFail(e);
        }
        return bitmapSubject;
    }

    private void onSuccess(String path) {
        if (bitmapSubject != null) {
            bitmapSubject.onNext(path);
            bitmapSubject.onCompleted();
        }
    }

    private void onFail(Throwable t) {
        bitmapSubject.onError(t);
    }

}
