package io.mindjet.jetdemo.viewmodel.item;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.view.View;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ItemSimpleBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;

/**
 * Created by Jet on 5/26/17.
 */

public class SimpleViewModel extends BaseViewModel<ViewInterface<ItemSimpleBinding>> {

    private SimpleViewModel.Builder mBuilder;

    private SimpleViewModel(Builder builder) {
        mBuilder = builder;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_simple;
    }

    public String getContent() {
        return mBuilder.contentRes == 0 ? mBuilder.content : getString(mBuilder.contentRes);
    }

    public Drawable getBackground() {
        return new ColorDrawable(mBuilder.backgroundRes == 0 ? Color.BLACK : getContext().getResources().getColor(mBuilder.backgroundRes));
    }

    public int getHeight() {
        return mBuilder.height == -1 ? -1 : getContext().getResources().getDimensionPixelOffset(mBuilder.height);
    }

    public int getTextColor() {
        return getContext().getResources().getColor(mBuilder.textColor);
    }

    @Override
    public void onViewAttached(View view) {

    }

    public static class Builder {

        private String content = "";
        @StringRes
        private int contentRes = 0;
        @ColorRes
        private int backgroundRes;
        @ColorRes
        private int textColor = R.color.black;
        @DimenRes
        private int height = -1;

        public SimpleViewModel.Builder content(String content) {
            this.content = content;
            return this;
        }

        public SimpleViewModel.Builder contentRes(@StringRes int contentRes) {
            this.contentRes = contentRes;
            return this;
        }

        public SimpleViewModel.Builder backgroundRes(@ColorRes int backgroundRes) {
            this.backgroundRes = backgroundRes;
            return this;
        }

        public SimpleViewModel.Builder textColor(@ColorRes int textColor) {
            this.textColor = textColor;
            return this;
        }

        public SimpleViewModel.Builder height(@DimenRes int height) {
            this.height = height;
            return this;
        }

        public SimpleViewModel build() {
            return new SimpleViewModel(this);
        }

    }

}
