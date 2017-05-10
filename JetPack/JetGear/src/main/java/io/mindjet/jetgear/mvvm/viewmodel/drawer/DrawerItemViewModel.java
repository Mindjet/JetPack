package io.mindjet.jetgear.mvvm.viewmodel.drawer;

import android.support.annotation.DrawableRes;
import android.view.View;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemDrawerBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import rx.functions.Action0;

/**
 * Drawer item view model for Drawer view model.
 * <p>
 * Created by Mindjet on 2017/2/26.
 */

public class DrawerItemViewModel extends BaseViewModel<ViewInterface<ItemDrawerBinding>> {

    private String content = "...";
    private int icon = R.drawable.ic_like;
    private Action0 onClick;

    @Override
    public void onViewAttached(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_drawer;
    }

    public DrawerItemViewModel content(String content) {
        this.content = content;
        return this;
    }

    public DrawerItemViewModel icon(@DrawableRes int icon) {
        this.icon = icon;
        return this;
    }

    public DrawerItemViewModel onClick(Action0 onClick) {
        this.onClick = onClick;
        return this;
    }

    public void onClick() {
        if (onClick != null) onClick.call();
    }

    public String getContent() {
        return content;
    }

    public int getIcon() {
        return icon;
    }

}
