package io.mindjet.jetwidget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import io.mindjet.jetutil.view.MeasureUtil;

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
    private final int DEFAULT_DURATION = 2000;

    private final int DEFAULT_BALL_COLOR = Color.BLACK;

    private Canvas mCanvas;
    private int mWidth;
    private int mHeight;
    private int mDuration;
    private float mProgress;
    private float mRotateDegree;
    private float mOffset;
    private boolean mEclipsed;
    private float mPinchOffset;            //the offset when 2 balls start to pinch.

    private int mBallRadius;
    private int mBallColor;
    private Paint mBallPaint;
    private Paint mBezierPaint;

    private Path mBezierPath;

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
        initPath();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QLoadingView);
        mBallRadius = array.getDimensionPixelOffset(R.styleable.QLoadingView_ballRadius, MeasureUtil.dp2px(context, DEFAULT_BALL_RADIUS));
        mBallColor = array.getColor(R.styleable.QLoadingView_ballColor, DEFAULT_BALL_COLOR);
        mEclipsed = array.getBoolean(R.styleable.QLoadingView_eclipsed, false);
        mDuration = array.getInteger(R.styleable.QLoadingView_duration, DEFAULT_DURATION);
        array.recycle();
    }

    private void initPaint() {
        mBallPaint = new Paint();
        mBallPaint.setColor(mBallColor);
        mBallPaint.setAntiAlias(true);
        mBezierPaint = new Paint();
        mBezierPaint.setColor(mBallColor);
        mBezierPaint.setAntiAlias(true);
        mBezierPaint.setStyle(Paint.Style.FILL);
    }

    private void initPath() {
        mBezierPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureUtil.getSizeFromMeasuredSpec(getContext(), widthMeasureSpec, DEFAULT_WIDTH);
        mHeight = MeasureUtil.getSizeFromMeasuredSpec(getContext(), heightMeasureSpec, DEFAULT_HEIGHT);
        mWidth = Math.min(mWidth, mHeight);
        mHeight = mWidth;
        setMeasuredDimension(mWidth, mHeight);
        mBallRadius = mBallRadius >= mWidth / 6 ? mWidth / 6 : mBallRadius;     //constraint the radius of balls.
        mPinchOffset = mHeight / 2 - mBallRadius * 2;
        startDraw();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        drawLoadingView();
    }

    private void startDraw() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(mDuration);
        animator.setInterpolator(mEclipsed ? new LinearInterpolator() : new FastOutSlowInInterpolator());
        animator.setRepeatCount(-1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (float) animation.getAnimatedValue();
                mOffset = (mHeight / 2 - mBallRadius) * Math.abs(mProgress - 0.5f) * 2;
                mRotateDegree = mProgress * 360;
                postInvalidate();
            }
        });
        animator.start();
    }

    private void drawLoadingView() {
        mCanvas.rotate(mRotateDegree, mWidth / 2, mHeight / 2);
        mCanvas.drawCircle(mWidth / 2, mBallRadius + (mEclipsed ? mOffset : 0), mBallRadius, mBallPaint);
        mCanvas.drawCircle(mWidth / 2, mHeight - mBallRadius - (mEclipsed ? mOffset : 0), mBallRadius, mBallPaint);
        //if eclipse is enabled and 2 balls start to pinch, draw bezier path.
        if (mEclipsed && mOffset >= mPinchOffset - mBallRadius / 2) drawBezierPath();
    }

    private void drawBezierPath() {
        mBezierPath.reset();
        float controlOffset = mOffset - mPinchOffset;
        float leftControlX = mWidth / 2 - controlOffset;        //from mWidth/2 to mWidth/2-mBallRadius.
        float rightControlX = mWidth / 2 + controlOffset;       //from mWidth/2 to mWidth/2+mBallRadius.

        //draw the path clockwise.
        mBezierPath.moveTo(mWidth / 2 - mBallRadius, mBallRadius + mOffset);
        mBezierPath.lineTo(mWidth / 2 + mBallRadius, mBallRadius + mOffset);
        mBezierPath.quadTo(rightControlX, mHeight / 2, mWidth / 2 + mBallRadius, mHeight - mBallRadius - mOffset);
        mBezierPath.lineTo(mWidth / 2 - mBallRadius, mHeight - mBallRadius - mOffset);
        mBezierPath.quadTo(leftControlX, mHeight / 2, mWidth / 2 - mBallRadius, mBallRadius + mOffset);
        mBezierPath.close();
        mCanvas.drawPath(mBezierPath, mBezierPaint);
    }

    public void setBallColor(@ColorInt int ballColor) {
        mBallPaint.setColor(ballColor);
        mBezierPaint.setColor(ballColor);
    }

    public void setEclipsed(boolean eclipsed) {
        mEclipsed = eclipsed;
    }
}
