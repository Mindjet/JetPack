package io.mindjet.jetgear.mvvm.listener;

/**
 * Created by Jet on 2/22/17.
 */

public interface LifeCycleListener {

    void onDestroy();

    void onStop();

    void onResume();

    void onBackPressed();

}
