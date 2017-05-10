package io.mindjet.jetgear.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;

import io.mindjet.jetutil.logger.JLogger;

/**
 * Base Dialog
 * <p>
 * Created by Mindjet on 3/20/17.
 */

public abstract class BaseDialog extends AppCompatDialog {

    protected JLogger jLogger = JLogger.get(getClass().getSimpleName());

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        beforeInitView();
        initView();
        initData();
    }

    protected abstract void beforeInitView();

    protected abstract void initView();

    protected abstract void initData();

}
