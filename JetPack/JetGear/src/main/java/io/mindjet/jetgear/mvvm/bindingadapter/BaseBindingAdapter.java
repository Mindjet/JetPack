package io.mindjet.jetgear.mvvm.bindingadapter;

import android.databinding.BindingAdapter;
import android.os.Build;
import android.view.View;

/**
 * Created by Jet on 2/20/17.
 */

public class BaseBindingAdapter {

    @BindingAdapter("app:elevation")
    public static void elevation(View view, float elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(elevation);
        }
    }

    @BindingAdapter("app:elevation")
    public static void elevationBoolean(View view, boolean elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(5f);
        }
    }

}
