package io.mindjet.jetwidget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;

/**
 * A cute loading view.
 * <p>
 * Created by Mindjet on 5/22/17.
 */

public class QLoadingView extends View {

    private final String TAG = "QLoadingView";

    private final int DEFAULT_WIDTH = 40;
    private final int DEFAULT_HEIGHT = 40;
    private final int DEFAULT_BALL_RADIUS = 8;

    private final int DEFAULT_BALL_COLOR = Color.BLACK;

    private Canvas mCanvas;
    private int mWidth;
    private int mHeight;
    private float mRotateDegree;
    private float mOffset;

    private int mBallRadius;

    private int mBallColor;

    private Paint mBallPaint;

    public QLoadingView(Context context) {
        this(context, null);
    }

    public QLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, io.mindjet.jetwidget.R.styleable.QLoadingView);
        mBallRadius = array.getDimensionPixelOffset(io.mindjet.jetwidget.R.styleable.QLoadingView_ballRadius, ViewUtil.dp2px(context, DEFAULT_BALL_RADIUS));
        mBallColor = array.getColor(io.mindjet.jetwidget.R.styleable.QLoadingView_ballColor, DEFAULT_BALL_COLOR);
        array.recycle();
    }

    private void initPaint() {
        mBallPaint = new Paint();
        mBallPaint.setColor(mBallColor);
        mBallPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = ViewUtil.getSizeFromMeasuredSpec(getContext(), widthMeasureSpec, DEFAULT_WIDTH);
        mHeight = ViewUtil.getSizeFromMeasuredSpec(getContext(), heightMeasureSpec, DEFAULT_HEIGHT);
        mWidth = Math.min(mWidth, mHeight);
        mHeight = mWidth;
        setMeasuredDimension(mWidth, mHeight);
        mBallRadius = mBallRadius >= mWidth / 6 ? mWidth / 6 : mBallRadius;     //constraint the radius of balls.
        startDraw();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        drawLoadingView();
    }

    private void startDraw() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(2000);
        animator.setInterpolator(new FastOutSlowInInterpolator());
        animator.setRepeatCount(-1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mOffset = (mHeight / 2 - mBallRadius) * Math.abs(value - 0.5f) * 2;
                mRotateDegree = value * 360;
                postInvalidate();
            }
        });
        animator.start();
    }

    private void drawLoadingView() {
        mCanvas.rotate(mRotateDegree, mWidth / 2, mHeight / 2);
        mCanvas.drawCircle(mWidth / 2, mBallRadius + mOffset, mBallRadius, mBallPaint);
        mCanvas.drawCircle(mWidth / 2, mHeight - mBallRadius - mOffset, mBallRadius, mBallPaint);
    }

}
