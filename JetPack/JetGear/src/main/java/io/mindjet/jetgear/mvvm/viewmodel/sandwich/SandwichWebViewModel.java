package io.mindjet.jetgear.mvvm.viewmodel.sandwich;

import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.databinding.IncludeSandwichLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;

/**
 * A webview model with sandwich feature.
 * <p>
 * Created by Mindjet on 5/26/17.
 */

public abstract class SandwichWebViewModel<V extends ViewInterface<IncludeSandwichLayoutBinding>> extends SandwichLayoutViewModel<V> {

    protected SandwichWebHeaderViewModel mHeader;
    protected SandwichWebContentViewModel mContent;

    @Override
    protected void initDummyStatusbarStyle(View dummyStatusbar) {
        dummyStatusbar.setBackgroundColor(getMainColor());
    }

    @Override
    protected void initSandwichTop(ViewGroup container) {
        mHeader = new SandwichWebHeaderViewModel();
        ViewModelBinder.bind(container, mHeader);
    }

    @Override
    protected void initSandwichMiddle(ViewGroup container) {
        mContent = new SandwichWebContentViewModel();
        ViewModelBinder.bind(container, mContent);
    }

    @Override
    protected void initSandwichBottom(ViewGroup container) {

    }

    @ColorInt
    protected abstract int getMainColor();

}
