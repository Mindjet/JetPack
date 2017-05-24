package io.mindjet.jetutil.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.Random;

import io.mindjet.jetutil.logger.JLogger;

/**
 * Utility for color.
 * <p>
 * Created by Mindjet on 3/2/17.
 */

public class ColorUtil extends View {

    private static JLogger jLogger = JLogger.get("ColorUtil");

    public ColorUtil(Context context) {
        super(context);
    }

    public static ColorStateList createColorStateList(int normalColor, int selectedColor) {
        final int[][] states = new int[2][];
        final int[] colors = new int[2];

        states[0] = View.SELECTED_STATE_SET;
        colors[0] = selectedColor;

        // Default enabled state
        states[1] = EMPTY_STATE_SET;
        colors[1] = normalColor;

        return new ColorStateList(states, colors);
    }

    public static int randomColor() {
        Random random = new Random();
        return 0xff000000 | random.nextInt(0x00ffffff);
    }

    public static int extractColor(Drawable drawable) {
        if (drawable instanceof ColorDrawable) {
            return ((ColorDrawable) drawable).getColor();
        } else {
            return Color.TRANSPARENT;
        }
    }

    public static int getBackgroundColor(View view) {
        Drawable background = view.getBackground();
        return extractColor(background);
    }

}
