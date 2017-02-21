package io.mindjet.jetgear.mvvm.viewmodel.header;

import android.databinding.BindingAdapter;
import android.support.annotation.ColorRes;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeHeaderBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;

/**
 * Created by Jet on 2/21/17.
 */

public class HeaderViewModel extends BaseViewModel<ViewInterface<IncludeHeaderBinding>> {

    private Builder builder;

    public HeaderViewModel(Builder builder) {
        this.builder = builder;
    }

    @Override
    public void onViewAttached(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.include_header;
    }

    public boolean getWithElevation() {
        return builder.withElevation;
    }

    public HeaderItemViewModel getLeftViewModel() {
        return builder.leftViewModel;
    }

    public HeaderItemViewModel getCenterViewModel() {
        return builder.centerViewModel;
    }

    public HeaderItemViewModel getRightViewModel() {
        return builder.rightViewModel;
    }

    public int getBackground() {
        return getContext().getResources().getColor(builder.background);
    }

    public static class Builder {
        @ColorRes
        public int background = R.color.colorPrimary;
        public HeaderItemViewModel leftViewModel, centerViewModel, rightViewModel;
        public boolean withElevation = true;

        public Builder background(@ColorRes int background) {
            this.background = background;
            return this;
        }

        public Builder leftViewModel(HeaderItemViewModel leftViewModel) {
            this.leftViewModel = leftViewModel;
            return this;
        }

        public Builder centerViewModel(HeaderItemViewModel centerViewModel) {
            this.centerViewModel = centerViewModel;
            return this;
        }

        public Builder rightViewModel(HeaderItemViewModel rightViewModel) {
            this.rightViewModel = rightViewModel;
            return this;
        }

        public Builder withElevation(boolean withElevation) {
            this.withElevation = withElevation;
            return this;
        }

        public HeaderViewModel build() {
            return new HeaderViewModel(this);
        }

    }

    @BindingAdapter("app:vm")
    public static void attachViewModel(ViewGroup container, HeaderItemViewModel viewModel) {
        if (viewModel != null) ViewModelBinder.bind(container, viewModel);
    }

}
