package io.mindjet.jetgear.mvvm.bindingadapter;

import android.animation.LayoutTransition;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetutil.logger.JLogger;

/**
 * Created by Jet on 2/20/17.
 */

public class BaseBindingAdapter {

    private static JLogger jLogger = JLogger.get(BaseBindingAdapter.class.getSimpleName());

    @BindingAdapter("app:layout_height")
    public static void height(View view, int height) {
        if (height <= 0) {
            jLogger.w("invalid height");
            view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            view.getLayoutParams().height = height;
        }
    }

    @BindingAdapter("app:elevation")
    public static void elevation(View view, float elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(elevation);
        }
    }

    @BindingAdapter("app:elevation")
    public static void elevationBoolean(View view, boolean elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(view.getContext().getResources().getInteger(R.integer.common_elevation));
        }
    }

    @BindingAdapter("app:enable_changeAnim")
    public static void enableLayoutChangeAnim(ViewGroup viewGroup, boolean enable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            viewGroup.setLayoutTransition(enable ? new LayoutTransition() : null);
        }
    }

}
