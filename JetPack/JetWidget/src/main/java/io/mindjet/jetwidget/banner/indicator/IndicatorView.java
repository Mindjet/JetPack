package io.mindjet.jetwidget.banner.indicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import io.mindjet.jetwidget.R;

/**
 * Base banner indicator view.
 * <p>
 * Created by Mindjet on 5/15/17.
 */

public class IndicatorView extends RelativeLayout {

    protected final String TAG = getClass().getSimpleName();
    protected int num;

    private View mRootView;
    private LinearLayout mContainer;
    private ImageView mCursor;
    private float mCursorLeft = 0f;
    private int mCursorWidth;
    private int mCursorOffset;
    private int mIndicatorWidth;
    private IndicatorInterface indicatorInterface;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr();
        initView();
        drawIndicators();
    }

    private void initAttr() {

    }

    private void initView() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.indicator_view, this);
        mContainer = (LinearLayout) mRootView.findViewById(R.id.container);
        mCursor = (ImageView) mRootView.findViewById(R.id.iv_cursor);
    }

    /**
     * Draw indicators according to IndicatorInterface.
     */
    private void drawIndicators() {
        if (num == 0 || num == 1) {
            mCursor.setVisibility(GONE);
            return;
        }
        mCursor.setImageDrawable(getIndicatorInterface().getSelectedDrawable());
        mCursorWidth = measureIndicatorWidth(mCursor);
        mCursor.setVisibility(VISIBLE);
        for (int i = 0; i < num; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(getIndicatorInterface().getNormalDrawable());
            mIndicatorWidth = measureIndicatorWidth(imageView);
            imageView.setPadding(i != 0 ? getIndicatorInterface().getIndicatorPadding() : 0, 0, 0, 0);
            mContainer.addView(imageView);
        }
        initCursorPosition();
    }

    public void setIndicatorStyle(IndicatorInterface style) {
        this.indicatorInterface = style;
        mContainer.removeAllViews();       //redraw the indicators.
        drawIndicators();
    }

    /**
     * Get the IndicatorInterface for the BannerView, return {@link IndicatorInterfaceImpl} if null.
     *
     * @return IndicatorInterface for the BannerView.
     */
    public IndicatorInterface getIndicatorInterface() {
        indicatorInterface = indicatorInterface == null ? new IndicatorInterfaceImpl(getContext()) : indicatorInterface;
        return indicatorInterface;
    }

    /**
     * Measure and return the width of one indicators
     *
     * @param imageView indicator image view.
     * @return the measured width of indicator.
     */
    private int measureIndicatorWidth(ImageView imageView) {
        imageView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return imageView.getMeasuredWidth();
    }

    /**
     * When the adapter num changes, this method is called to update the number of indicators.
     *
     * @param num the num of ViewPager's adapter.
     */
    public void onIndicatorNumChanged(int num) {
        this.num = num;
        mContainer.removeAllViews();       //redraw the indicators.
        drawIndicators();
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelected(int position) {
        translateCursorX(position);
    }

    /**
     * Init the position of the cursor according to {@link #mCursorWidth} and {@link #mIndicatorWidth}.
     */
    private void initCursorPosition() {
        mCursorOffset = 0 - (mCursorWidth - mIndicatorWidth) / 2;
        translateCursorX(0);
    }

    private void translateCursorX(int position) {
        final float after = mCursorOffset + position * (getIndicatorInterface().getIndicatorPadding() + mIndicatorWidth);
        ObjectAnimator animator = ObjectAnimator.ofFloat(mCursor, "translationX", mCursorLeft, after);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCursorLeft = after;
            }
        });
        animator.start();
    }

    public static IndicatorInterface create(final Context context, @DrawableRes int normal, @DrawableRes int selected, @DimenRes int padding) {
        return create(context, context.getResources().getDrawable(normal), context.getResources().getDrawable(selected), padding);
    }

    public static IndicatorInterface create(final Context context, final Drawable normal, final Drawable selected, @DimenRes final int padding) {
        return new IndicatorInterface() {
            @Override
            public Context getContext() {
                return context;
            }

            @Override
            public Drawable getNormalDrawable() {
                return normal;
            }

            @Override
            public Drawable getSelectedDrawable() {
                return selected;
            }

            @Override
            public int getIndicatorPadding() {
                return context.getResources().getDimensionPixelOffset(padding);
            }
        };
    }


}
