package io.mindjet.jetwidget.banner;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * A scroller to override the default scroll in ViewPager to change the default scrolling speed.
 * <p>
 * Created by Mindjet on 5/15/17.
 */

public class SpeedScroller extends Scroller {

    private int mDuration = 1000;

    public SpeedScroller(Context context) {
        super(context);
    }

    public SpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }

}
