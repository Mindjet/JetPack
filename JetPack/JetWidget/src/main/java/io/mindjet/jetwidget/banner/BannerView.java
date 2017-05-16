package io.mindjet.jetwidget.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

import io.mindjet.jetwidget.R;
import io.mindjet.jetwidget.banner.indicator.IndicatorInterface;
import io.mindjet.jetwidget.banner.indicator.IndicatorView;

/**
 * Common banner view.
 * <p>
 * Created by Mindjet on 5/15/17.
 */

public class BannerView extends FrameLayout {

    private static final String TAG = BannerView.class.getSimpleName();

    private View mRootView;
    private ViewPager mViewPager;
    private FrameLayout mIndicatorContainer;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private AutoScrollTask mScrollTask;
    private IndicatorView mIndicatorView;

    /*Attributes*/
    private boolean autoScroll;
    private int scrollInterval;
    private int scrollDuration;  //ViewPager's default scroll duration

    public BannerView(@NonNull Context context) {
        this(context, null);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initView();
        initTouchEvent();
    }

    /**
     * Init attributes.
     *
     * @param context context.
     * @param attrs   attribute set.
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BannerView);
        scrollInterval = typedArray.getInt(R.styleable.BannerView_scrollInterval, 1000);
        autoScroll = typedArray.getBoolean(R.styleable.BannerView_autoScroll, true);
        scrollDuration = typedArray.getInt(R.styleable.BannerView_scrollDuration, 250);
        typedArray.recycle();
    }

    /**
     * Init view.
     */
    private void initView() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.banner_view, this);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.view_pager);
        mIndicatorContainer = (FrameLayout) mRootView.findViewById(R.id.indicator_container);
        mIndicatorView = new IndicatorView(getContext());
        mIndicatorContainer.addView(mIndicatorView);
        mViewPager.addOnPageChangeListener(new BannerPageChangeListener());
        setScrollDuration(scrollDuration);
    }

    /**
     * Init touch event.
     * <p>
     * Auto-scrolling should be stopped when the finger down, and resumed when the finger up.
     */
    private void initTouchEvent() {
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        stopAutoScroll();
                        break;
                    case MotionEvent.ACTION_UP:
                        startAutoScroll();
                        break;
                }
                return false;   //the touch event shouldn't be consumed, otherwise the view pager cannot be scrolled by user.
            }
        });

        //pass touch event from view pager container to view pager.
        mRootView.findViewById(R.id.view_pager_container).setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
    }

    private void startAutoScroll() {
        if (mScrollTask != null && autoScroll) mScrollTask.start();
    }

    private void stopAutoScroll() {
        if (mScrollTask != null) mScrollTask.stop();
    }

    private void notifyAdapterSizeChanged(int size) {
        mIndicatorView.onIndicatorSizeChanged(size);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mScrollTask == null) mScrollTask = new AutoScrollTask();
        startAutoScroll();
    }

    private class BannerPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mIndicatorView.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            mIndicatorView.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class AutoScrollTask implements Runnable {

        @Override
        public void run() {
            int pos = mViewPager.getCurrentItem();
            if (pos == mViewPager.getAdapter().getCount() - 1) {
                mViewPager.setCurrentItem(0);
            } else {
                mViewPager.setCurrentItem(pos + 1);
            }
            mHandler.postDelayed(this, scrollInterval + scrollDuration);
        }

        void start() {
            mHandler.removeCallbacks(this);
            mHandler.postDelayed(this, scrollInterval);
        }

        void stop() {
            mHandler.removeCallbacks(this);
        }

    }

    /*---------------Exposed API---------------*/

    public void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        notifyAdapterSizeChanged(adapter.getCount());
    }

    public PagerAdapter getAdapter() {
        return mViewPager.getAdapter();
    }

    public void setScrollDuration(int duration) {
        scrollDuration = duration;
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            SpeedScroller scroller = new SpeedScroller(getContext(), new FastOutSlowInInterpolator());
            scroller.setDuration(duration);
            field.set(mViewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setScrollInterval(int interval) {
        scrollInterval = interval;
    }

    public void setIndicatorStyle(IndicatorInterface style) {
        mViewPager.setCurrentItem(0);
        mIndicatorView.setIndicatorInterface(style);
        notifyAdapterSizeChanged(getAdapter().getCount());
    }
}
