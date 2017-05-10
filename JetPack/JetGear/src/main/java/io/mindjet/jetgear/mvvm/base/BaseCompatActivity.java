package io.mindjet.jetgear.mvvm.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import io.mindjet.jetutil.manager.ActivityManager;

/**
 * Base AppCompatActivity pushed to the ActivityManager when created and pulled out when destroyed.
 * <p>
 * Created by Mindjet on 2/28/17.
 */

public class BaseCompatActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ActivityManager.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.finishActivity(this);
    }
}
