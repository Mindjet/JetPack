package io.mindjet.jetutil.drawable;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;

import io.mindjet.jetutil.version.VersionUtil;
import io.mindjet.jetutil.view.ColorUtil;

/**
 * Utility to dye drawable.
 * <p>
 * Created by Mindjet on 5/11/17.
 */

public class DrawableDyer {

    public static Drawable dye(Drawable drawable, @ColorInt int color) {
        return commonDye(drawable, color);
    }

    public static Drawable dyeRandomColor(Drawable drawable) {
        return commonDye(drawable, ColorUtil.randomColor());
    }

    private static Drawable commonDye(Drawable drawable, int color) {
        drawable.mutate();
        Drawable output = DrawableCompat.wrap(drawable);
        if (VersionUtil.afterAPI(21)) {
            output.setTint(color);
        } else {
            output.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        return output;
    }

}
