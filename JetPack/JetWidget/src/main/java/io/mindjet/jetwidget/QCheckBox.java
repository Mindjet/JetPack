package io.mindjet.jetwidget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Checkable;

/**
 * A cute CheckBox.
 * <p>
 * Created by Mindjet on 5/19/17.
 */

public class QCheckBox extends View implements Checkable {

    private final String TAG = "QCheckBox";

    private final int DEFAULT_BORDER_WIDTH = 2;              //in dp
    private final int DEFAULT_CHECK_WIDTH = 2;              //in dp
    private final int DEFAULT_BORDER_COLOR = Color.DKGRAY;
    private final int DEFAULT_CONTENT_COLOR = Color.RED;
    private final int DEFAULT_CHECK_COLOR = Color.WHITE;
    private final int DEFAULT_ANIMATION_DURATION = 300;

    private final int DEFAULT_WIDTH = 20;
    private final int DEFAULT_HEIGHT = 20;

    private boolean mChecked;

    private Paint mBorderPaint;
    private Paint mContentPaint;
    private Paint mCheckPaint;
    private Paint mUncheckedBgPaint;
    private Paint mContentEraser;

    private Point[] mCheckPoints = new Point[3];
    private float mLeftCheckLength;
    private float mRightCheckLength;

    private int mWidth;
    private int mHeight;
    private int mBorderWidth;       //in pixel
    private int mCheckWidth;        //in pixel

    private float mProgress;
    private float mCheckProgress;

    private Canvas mCanvas;

    private OnCheckedChangedListener onCheckedChangedListener;

    @ColorInt
    private int mBorderColor, mContentColor, mCheckColor;

    public QCheckBox(Context context) {
        this(context, null);
    }

    public QCheckBox(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QCheckBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaint();
        initView();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QCheckBox);
        mBorderColor = typedArray.getColor(R.styleable.QCheckBox_borderColor, DEFAULT_BORDER_COLOR);
        mContentColor = typedArray.getColor(R.styleable.QCheckBox_contentColor, DEFAULT_CONTENT_COLOR);
        mCheckColor = typedArray.getColor(R.styleable.QCheckBox_checkColor, DEFAULT_CHECK_COLOR);
        mBorderWidth = typedArray.getDimensionPixelOffset(R.styleable.QCheckBox_borderWidth, ViewUtil.dp2px(context, DEFAULT_BORDER_WIDTH));
        mCheckWidth = typedArray.getDimensionPixelOffset(R.styleable.QCheckBox_checkWidth, ViewUtil.dp2px(context, DEFAULT_CHECK_WIDTH));
        mChecked = typedArray.getBoolean(R.styleable.QCheckBox_checked, false);
        mProgress = mChecked ? 1 : 0;
        mCheckProgress = mChecked ? 1 : 0;
        typedArray.recycle();
    }

    private void initPaint() {
        mBorderPaint = makePaint(mBorderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mContentPaint = makePaint(mContentColor);

        mCheckPaint = makePaint(mCheckColor);
        mCheckPaint.setStrokeWidth(mCheckWidth);
        mCheckPaint.setStrokeCap(Paint.Cap.ROUND);

        mUncheckedBgPaint = makePaint(mBorderColor);     //paint to draw background when from checked to unchecked.

        mContentEraser = makePaint(Color.WHITE);    //paint to erase background.
    }

    private Paint makePaint(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        return paint;
    }

    private void initView() {
        setBackgroundResource(R.drawable.ripple_borderless_default);
        setClickable(true);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = ViewUtil.getSizeFromMeasuredSpec(getContext(), widthMeasureSpec, DEFAULT_WIDTH);
        mHeight = ViewUtil.getSizeFromMeasuredSpec(getContext(), heightMeasureSpec, DEFAULT_HEIGHT);
        setMeasuredDimension(mWidth, mHeight);
        initCheckPoints();
//        printBasicInfo();
    }

    private void initCheckPoints() {
        mCheckPoints[0] = new Point(Math.round(mWidth * 2 / 9), Math.round(mHeight / 2));
        mCheckPoints[1] = new Point(Math.round(mWidth * 2 / 5), Math.round(mHeight * 17 / 24));
        mCheckPoints[2] = new Point(Math.round(mWidth * 41 / 54), Math.round(mHeight * 31 / 96));
        mLeftCheckLength = (float) Math.sqrt(Math.pow((mCheckPoints[1].x - mCheckPoints[0].x), 2) + Math.pow((mCheckPoints[1].y - mCheckPoints[0].y), 2));
        mRightCheckLength = (float) Math.sqrt(Math.pow((mCheckPoints[2].x - mCheckPoints[1].x), 2) + Math.pow((mCheckPoints[2].y - mCheckPoints[1].y), 2));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        drawCheckBox();
    }

    private void drawCheckBox() {
        if (mChecked) {
            mCanvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 * mProgress, mContentPaint);
            drawCheck();
        } else {
            mCanvas.drawCircle(mWidth / 2, mHeight / 2, (mWidth - mBorderWidth) / 2, mUncheckedBgPaint);
            mCanvas.drawCircle(mWidth / 2, mHeight / 2, (mWidth - mBorderWidth) / 2 * (1 - mProgress), mContentEraser);
            mCanvas.drawCircle(mWidth / 2, mHeight / 2, (mWidth - mBorderWidth) / 2 * (1 - mProgress), mBorderPaint);        //the third parameter control the size of the circle.
        }
    }

    private void drawCheck() {
        float mDrawnLength = mCheckProgress * (mLeftCheckLength + mRightCheckLength);
        float targetX, targetY;
        if (mDrawnLength < mLeftCheckLength) {
            targetX = mCheckPoints[0].x + (mCheckPoints[1].x - mCheckPoints[0].x) * mDrawnLength / mLeftCheckLength;
            targetY = mCheckPoints[0].y + (mCheckPoints[1].y - mCheckPoints[0].y) * mDrawnLength / mLeftCheckLength;
            mCanvas.drawLine(mCheckPoints[0].x, mCheckPoints[0].y, targetX, targetY, mCheckPaint);
        } else {
            mCanvas.drawLine(mCheckPoints[0].x, mCheckPoints[0].y, mCheckPoints[1].x, mCheckPoints[1].y, mCheckPaint);
            targetX = mCheckPoints[1].x + (mCheckPoints[2].x - mCheckPoints[1].x) * (mDrawnLength - mLeftCheckLength) / mRightCheckLength;
            targetY = mCheckPoints[1].y + (mCheckPoints[2].y - mCheckPoints[1].y) * (mDrawnLength - mLeftCheckLength) / mRightCheckLength;
            mCanvas.drawLine(mCheckPoints[1].x, mCheckPoints[1].y, targetX, targetY, mCheckPaint);
        }
    }

    private void printBasicInfo() {
        Log.e(TAG, "default width: " + mWidth);
        Log.e(TAG, "default height: " + mHeight);
        Log.e(TAG, "getMeasuredWidth: " + getMeasuredWidth());
        Log.e(TAG, "getMeasuredHeight: " + getMeasuredHeight());
        Log.e(TAG, "getLeft: " + getLeft());
        Log.e(TAG, "getTop: " + getTop());
        Log.e(TAG, "getTranslationX: " + getTranslationX());
        Log.e(TAG, "getTranslationY: " + getTranslationY());
    }

    private void startCheckedAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.7f, 0.5f, 1f);
        animator.setDuration(DEFAULT_ANIMATION_DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
        mCheckProgress = 0;
        ValueAnimator checkAnimator = ValueAnimator.ofFloat(0f, 1f);
        checkAnimator.setDuration(DEFAULT_ANIMATION_DURATION);
        checkAnimator.setStartDelay(DEFAULT_ANIMATION_DURATION);
        checkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCheckProgress = (float) valueAnimator.getAnimatedValue();
                postInvalidate();
            }
        });
        checkAnimator.start();
    }

    private void startUncheckedAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.5f, 0.7f, 0f);
        animator.setDuration(DEFAULT_ANIMATION_DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    public void setOnCheckedChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
        this.onCheckedChangedListener = onCheckedChangedListener;
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void setChecked(boolean checked) {
        mChecked = checked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
        if (onCheckedChangedListener != null)
            onCheckedChangedListener.onCheckedChanged(this, isChecked());
        if (mChecked) {
            startCheckedAnim();
        } else {
            startUncheckedAnim();
        }
    }

    public interface OnCheckedChangedListener {
        void onCheckedChanged(QCheckBox checkBox, boolean checked);
    }

}
