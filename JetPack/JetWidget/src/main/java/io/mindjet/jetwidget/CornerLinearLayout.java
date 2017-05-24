package io.mindjet.jetwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import io.mindjet.jetutil.logger.JLogger;
import io.mindjet.jetutil.view.ColorUtil;

/**
 * A LinearLayout with changeable corner radius.
 * <p>
 * Created by Mindjet on 5/23/17.
 */

public class CornerLinearLayout extends LinearLayout {

    private static JLogger jLogger = JLogger.get(CornerLinearLayout.class.getSimpleName());

    private final float DEFAULT_CORNER_RADIUS = 0;

    private float mWidth;
    private float mHeight;

    private float mCornerRadius;
    private Paint mCornerPaint;
    private int mCornerColor;
    private Path mPath;

    public CornerLinearLayout(Context context) {
        this(context, null);
    }

    public CornerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        initPaint();
        initPath();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CornerLinearLayout);
        mCornerRadius = typedArray.getDimension(R.styleable.CornerLinearLayout_cornerRadius, DEFAULT_CORNER_RADIUS);
        typedArray.recycle();
    }

    private void initPaint() {
        mCornerColor = ColorUtil.getBackgroundColor(this);
        mCornerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCornerPaint.setColor(mCornerColor);
        mCornerPaint.setStyle(Paint.Style.FILL);
    }

    private void initPath() {
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        super.setBackgroundColor(Color.TRANSPARENT);
        drawCornerRect(canvas);
    }

    private void drawCornerRect(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(mCornerRadius, 0);
        mPath.lineTo(mWidth - mCornerRadius, 0);
        mPath.quadTo(mWidth, 0, mWidth, mCornerRadius);
        mPath.lineTo(mWidth, mHeight - mCornerRadius);
        mPath.quadTo(mWidth, mHeight, mWidth - mCornerRadius, mHeight);
        mPath.lineTo(mCornerRadius, mHeight);
        mPath.quadTo(0, mHeight, 0, mHeight - mCornerRadius);
        mPath.lineTo(0, mCornerRadius);
        mPath.quadTo(0, 0, mCornerRadius, 0);
        mPath.close();
        canvas.drawPath(mPath, mCornerPaint);
    }

    public void setCornerRadius(float cornerRadius) {
        mCornerRadius = cornerRadius;
        invalidate();
    }

    @Override
    public void setBackground(Drawable background) {
        mCornerColor = ColorUtil.extractColor(background);
        setBackgroundColor(mCornerColor);
        super.setBackground(background);
    }

    @Override
    public void setBackgroundColor(@ColorInt int color) {
        mCornerColor = color;
        if (mCornerPaint != null) mCornerPaint.setColor(mCornerColor);
        invalidate();
    }

    @Override
    public Drawable getBackground() {
        return new ColorDrawable(mCornerColor);
    }

    @ColorInt
    public int getBackgroundColor() {
        return mCornerColor;
    }

    public float getCornerRadius() {
        return mCornerRadius;
    }
}
