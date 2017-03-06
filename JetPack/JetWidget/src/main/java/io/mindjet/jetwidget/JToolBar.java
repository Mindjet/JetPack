package io.mindjet.jetwidget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Jet on 3/6/17.
 */

public class JToolBar extends Toolbar {

    private boolean layoutReady;

    public JToolBar(Context context) {
        this(context, null);
    }

    public JToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (!layoutReady) {
            //if version is later than KitKat, an artificial status bar are supposed to added.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ViewGroup.LayoutParams lp = getLayoutParams();
                lp.height = getHeight() + getStatusBarHeight();
                int paddingLeft = getPaddingLeft();
                setPadding(paddingLeft, getStatusBarHeight(), 0, 0);
            }
            layoutReady = true;
        }
    }

    /**
     * Note that to use this method rather than {@link Toolbar#setNavigationIcon(int)}, as this method will set the icon centered vertically.
     *
     * @param icon the navigation icon.
     */
    public void setNavIcon(@DrawableRes int icon) {
        setNavigationIcon(icon);
        AppCompatTextView tv = (AppCompatTextView) getChildAt(0);
        tv.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = tv.getMeasuredHeight();
        AppCompatImageButton btn = (AppCompatImageButton) getChildAt(1);
        int paddingRight = getResources().getDimensionPixelOffset(R.dimen.common_gap);
        btn.setLayoutParams(new Toolbar.LayoutParams(height, height));
        btn.setPadding(0, 0, paddingRight, 0);
        btn.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.ripple_borderless));
        btn.setScaleType(ImageView.ScaleType.CENTER);
    }

    private int getStatusBarHeight() {
        return getResources().getDimensionPixelSize(R.dimen.tool_bar_status_bar_height);
    }

}
