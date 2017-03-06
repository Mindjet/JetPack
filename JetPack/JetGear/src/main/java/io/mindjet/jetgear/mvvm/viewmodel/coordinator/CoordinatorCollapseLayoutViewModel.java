package io.mindjet.jetgear.mvvm.viewmodel.coordinator;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeCoordinatorCollapseLayoutBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetwidget.JToolBar;

/**
 * Created by Jet on 3/2/17.
 */

public abstract class CoordinatorCollapseLayoutViewModel<V extends ViewInterface<IncludeCoordinatorCollapseLayoutBinding>> extends BaseViewModel<V> implements AppBarLayout.OnOffsetChangedListener {

    @Override
    public void onViewAttached(View view) {
        afterViewAttached();
        initCollapsingToolbar(getSelfView().getBinding().collapsingToolBar);
        initCollapsingContent(getSelfView().getBinding().collapsingContent);
        initToolbar(getSelfView().getBinding().toolbar);
        initContent(getSelfView().getBinding().llyContainer);
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_coordinator_collapse_layout;
    }

    protected abstract void initCollapsingToolbar(CollapsingToolbarLayout layout);

    protected abstract void initCollapsingContent(ViewGroup container);

    protected abstract void initContent(ViewGroup container);

    protected abstract void initToolbar(JToolBar toolbar);

    public abstract boolean createOptionMenu(Menu menu);

    protected abstract void afterViewAttached();

    protected abstract void onNavIconClick(View view);

    protected void setNavIcon(@DrawableRes int icon) {
        setNavIcon(getContext().getResources().getDrawable(icon));
    }

    protected void setNavIcon(Drawable drawable) {
        getToolbar().setNavigationIcon(drawable);
        getToolbar().setPadding(getToolbar().getPaddingLeft(), getToolbar().getPaddingTop(), getToolbar().getPaddingRight(), getToolbar().getPaddingBottom());
    }

    protected Toolbar getToolbar() {
        return getSelfView().getBinding().toolbar;
    }

    protected CollapsingToolbarLayout getCollapsingToolbarLayout() {
        return getSelfView().getBinding().collapsingToolBar;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        jLogger.e(verticalOffset);
    }

    protected View.OnClickListener getNavIconListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNavIconClick(v);
            }
        };
    }

}
