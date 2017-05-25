package io.mindjet.jetwidget.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mindjet on 5/25/17.
 */

public class BaseBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    public BaseBehavior() {
        
    }

    public BaseBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
