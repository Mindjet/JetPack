package io.mindjet.jetdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.mindjet.jetutil.logger.JLogger;

/**
 * Base Activity for demos.
 * <p>
 * Created by Mindjet on 2/16/17.
 */

public abstract class BaseDemoActivity extends AppCompatActivity {

    protected JLogger jLogger = JLogger.get(getClass().getSimpleName());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInitView();
        initView();
        initData();
    }

    public abstract void beforeInitView();

    public abstract void initView();

    public abstract void initData();

}
