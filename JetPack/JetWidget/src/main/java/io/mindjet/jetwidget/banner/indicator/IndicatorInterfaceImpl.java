package io.mindjet.jetwidget.banner.indicator;

import android.content.Context;
import android.graphics.drawable.Drawable;

import io.mindjet.jetwidget.R;
import io.mindjet.jetwidget.banner.indicator.IndicatorInterface;

/**
 * A simple implementation of IndicatorInterface.
 * <p>
 * Created by Mindjet on 5/16/17.
 */

public class IndicatorInterfaceImpl implements IndicatorInterface {

    private Context context;

    public IndicatorInterfaceImpl(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Drawable getNormalDrawable() {
        return getContext().getResources().getDrawable(R.drawable.shape_cursor_normal);
    }

    @Override
    public Drawable getSelectedDrawable() {
        return getContext().getResources().getDrawable(R.drawable.shape_cursor_selected);
    }

    @Override
    public int getIndicatorPadding() {
        return getContext().getResources().getDimensionPixelOffset(R.dimen.common_gap_small);
    }

}
