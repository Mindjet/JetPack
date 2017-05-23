package io.mindjet.jetutil.view;

import android.content.Context;
import android.support.annotation.Px;
import android.util.TypedValue;
import android.view.View;

/**
 * Utility for measurement of Views.
 * <p>
 * Created by Mindjet on 5/22/17.
 */

public class MeasureUtil {

    /**
     * Convert dip to pixel.
     *
     * @param context context.
     * @param dp      the value to convert.
     * @return the corresponding value in pixel.
     */
    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    /**
     * Each {@link android.view.View.MeasureSpec} consists of {@code MeasureSpecSize} and {@code MeasureSpecMode}.
     * <p>
     * This method get the size from {@link android.view.View.MeasureSpec} according to {@code MeasureSpecSize}, {@code MeasureSpecMode} and default size.
     *
     * @param measuredSpec MeasureSpec.
     * @param defaultSize  default size in pixel.
     */
    public static int getSizeFromMeasuredSpec(int measuredSpec, @Px int defaultSize) {
        int specMode = View.MeasureSpec.getMode(measuredSpec);
        int specSize = View.MeasureSpec.getSize(measuredSpec);
        switch (specMode) {
            case View.MeasureSpec.EXACTLY:
                return specSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
            default:
                return defaultSize;
        }
    }

    /**
     * Each {@link android.view.View.MeasureSpec} consists of {@code MeasureSpecSize} and {@code MeasureSpecMode}.
     * <p>
     * This method get the size from {@link android.view.View.MeasureSpec} according to {@code MeasureSpecSize}, {@code MeasureSpecMode} and default size.
     *
     * @param context      context.
     * @param measuredSpec MeasureSpec.
     * @param defaultSize  default size in dp.
     */
    public static int getSizeFromMeasuredSpec(Context context, int measuredSpec, int defaultSize) {
        int specMode = View.MeasureSpec.getMode(measuredSpec);
        int specSize = View.MeasureSpec.getSize(measuredSpec);
        switch (specMode) {
            case View.MeasureSpec.EXACTLY:
                return specSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
            default:
                return dp2px(context, defaultSize);
        }
    }

}
