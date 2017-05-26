package io.mindjet.jetgear.mvvm.viewmodel.coordinator;

import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeSandwichLayoutBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetutil.version.VersionUtil;

/**
 * A layout view model acts like a sandwich.
 * <p>
 * Created by Mindjet on 5/26/17.
 */

public abstract class SandwichLayoutViewModel<V extends ViewInterface<IncludeSandwichLayoutBinding>> extends BaseViewModel<V> {

    @Override
    public int getLayoutId() {
        return R.layout.include_sandwich_layout;
    }

    @Override
    public void onViewAttached(View view) {
        IncludeSandwichLayoutBinding binding = getSelfView().getBinding();
        afterViewAttached(binding);
        initDummyStatusbarStyle(binding.dummyStatusBar);
        initSandwichTop(binding.sandwichTop);
        initSandwichMiddle(binding.sandwichMiddle);
        initSandwichBottom(binding.sandwichBottom);
        afterComponentsBound(binding);
    }

    public boolean getDummyStatusbarVisibility() {
        return VersionUtil.afterKitKat();
    }

    protected abstract void initDummyStatusbarStyle(View dummyStatusbar);

    protected abstract void initSandwichTop(ViewGroup container);

    protected abstract void initSandwichMiddle(ViewGroup container);

    protected abstract void initSandwichBottom(ViewGroup container);

    protected void afterViewAttached(IncludeSandwichLayoutBinding binding) {

    }

    protected void afterComponentsBound(IncludeSandwichLayoutBinding binding) {

    }

}
