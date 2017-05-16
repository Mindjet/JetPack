package io.mindjet.jetwidget.banner.indicator;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Interface to define the style of banner indicator view.
 * <p>
 * Created by Mindjet on 5/15/17.
 */

public interface IndicatorInterface {

    Context getContext();

    Drawable getNormalDrawable();

    Drawable getSelectedDrawable();

    int getIndicatorPadding();

}
