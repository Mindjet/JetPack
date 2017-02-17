package io.mindjet.jetutil.logger;


import android.util.Log;

/**
 * Created by Jet on 2/16/17.
 */

public class JLogger {

    private String TAG;

    private JLogger(String TAG) {
        this.TAG = TAG;
    }

    public static JLogger get(String TAG) {
        return new JLogger(TAG);
    }

    private String tag() {
        Throwable t = new Throwable();
        String method = t.getStackTrace()[2].getMethodName();
        return TAG + "#" + method;
    }

    public void e(Object o) {
        Log.e(tag(), o.toString());
    }

    public void i(Object o) {
        Log.i(tag(), o.toString());
    }

}
