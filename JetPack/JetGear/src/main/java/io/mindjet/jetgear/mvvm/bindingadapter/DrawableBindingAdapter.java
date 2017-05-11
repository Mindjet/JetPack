package io.mindjet.jetgear.mvvm.bindingadapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.ImageView;

import io.mindjet.jetutil.drawable.DrawableDyer;
import io.mindjet.jetutil.logger.JLogger;

/**
 * {@link android.databinding.BindingAdapter} for Drawable.
 * <p>
 * Created by Mindjet on 5/11/17.
 */

public class DrawableBindingAdapter {

    public static JLogger jLogger = JLogger.get(DrawableBindingAdapter.class.getSimpleName());

    @BindingAdapter("dye_color")
    public static void dye(View view, @ColorInt int color) {
        Drawable origin = view.getBackground();
        if (origin == null) {
            jLogger.w("The view to dye has no background, please set background to enable the functionality");
        } else {
            view.setBackground(DrawableDyer.dye(origin, color));
        }
    }

    @BindingAdapter("dye_color")
    public static void dye(ImageView imageView, @ColorInt int color) {
        Drawable origin = imageView.getDrawable();
        origin = origin == null ? imageView.getBackground() : origin;
        if (origin == null) {
            jLogger.w("The ImageView to dye has no background, please set src(recommended) or background to enable this functionality");
        } else {
            imageView.setImageDrawable(DrawableDyer.dye(origin, color));
        }
    }

    @BindingAdapter("dye_random")
    public static void dye(View view, boolean random) {
        Drawable origin = view.getBackground();
        if (origin == null) {
            jLogger.w("The view to dye has no background, please set background to enable the functionality");
        } else if (random) {
            view.setBackground(DrawableDyer.dyeRandomColor(origin));
        }
    }

    @BindingAdapter("dye_random")
    public static void dye(ImageView imageView, boolean random) {
        Drawable origin = imageView.getDrawable();
        origin = origin == null ? imageView.getBackground() : origin;
        if (origin == null) {
            jLogger.w("The ImageView to dye has no background, please set src(recommended) or background to ensure the functionality");
        } else if (random) {
            imageView.setImageDrawable(DrawableDyer.dyeRandomColor(origin));
        }
    }

}
