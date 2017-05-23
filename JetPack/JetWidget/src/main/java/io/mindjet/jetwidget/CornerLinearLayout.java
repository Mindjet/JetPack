package io.mindjet.jetwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import io.mindjet.jetutil.view.ColorUtil;

/**
 * A LinearLayout with changeable corner radius.
 * <p>
 * Created by Mindjet on 5/23/17.
 */

public class CornerLinearLayout extends LinearLayout {

    private final String TAG = "CornerLinearLayout";

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
        if (mCornerRadius == 0) {
            return;
        }
        setBackgroundColor(Color.TRANSPARENT);
        drawCorners(canvas);
        drawContent(canvas);
    }

    private void drawCorners(Canvas canvas) {
        canvas.drawCircle(mCornerRadius, mCornerRadius, mCornerRadius, mCornerPaint);   //left top
        canvas.drawCircle(mWidth - mCornerRadius, mCornerRadius, mCornerRadius, mCornerPaint); //right top
        canvas.drawCircle(mCornerRadius, mHeight - mCornerRadius, mCornerRadius, mCornerPaint);      //left bottom
        canvas.drawCircle(mWidth - mCornerRadius, mHeight - mCornerRadius, mCornerRadius, mCornerPaint);       //right bottom
    }

    private void drawContent(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(mCornerRadius, 0);
        mPath.lineTo(mWidth - mCornerRadius, 0);
        mPath.lineTo(mWidth, mCornerRadius);
        mPath.lineTo(mWidth, mHeight - mCornerRadius);
        mPath.lineTo(mWidth - mCornerRadius, mHeight);
        mPath.lineTo(mCornerRadius, mHeight);
        mPath.lineTo(0, mHeight - mCornerRadius);
        mPath.lineTo(0, mCornerRadius);
        mPath.lineTo(mCornerRadius, 0);
        mPath.close();
        canvas.drawPath(mPath, mCornerPaint);
    }

}
