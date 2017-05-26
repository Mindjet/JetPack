package io.mindjet.jetgear.mvvm.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import io.mindjet.jetutil.logger.JLogger;

/**
 * Behavior for Sandwich View.
 * <p>
 * Created by Mindjet on 5/25/17.
 */

public class SandwichBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    private JLogger jLogger = JLogger.get(getClass().getSimpleName());

    private float mChildOriginTop = 0f;
    private float mChildOriginHeight = 0f;

    public SandwichBehavior() {

    }

    public SandwichBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, V child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, V child, View dependency) {
        float sandwichTopOffset = dependency.getTop();
        float sandwichTopHeight = dependency.getHeight();
        float progress = sandwichTopOffset / sandwichTopHeight;
        mChildOriginTop = mChildOriginTop == 0f ? child.getTop() : mChildOriginTop;
        //the height will change if the child is partly visible in which the value of height equals to the visible height.
        mChildOriginHeight = mChildOriginHeight == 0f ? child.getHeight() : mChildOriginHeight;
        child.setTop((int) Math.ceil(mChildOriginTop - progress * mChildOriginHeight));
        return true;
    }


}
