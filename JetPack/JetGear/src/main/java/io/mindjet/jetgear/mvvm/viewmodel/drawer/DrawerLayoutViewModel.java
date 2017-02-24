package io.mindjet.jetgear.mvvm.viewmodel.drawer;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeDrawerLayoutBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;

/**
 * Created by Jet on 2/24/17.
 */

public abstract class DrawerLayoutViewModel<V extends ViewInterface<IncludeDrawerLayoutBinding>> extends BaseViewModel<V> implements DrawerLayout.DrawerListener {

    private DrawerLayout drawerLayout;

    @Override
    public void onViewAttached(View view) {
        drawerLayout = getSelfView().getBinding().drawerLayout;
        drawerLayout.addDrawerListener(this);
        initHeader(getSelfView().getBinding().llyHeader);
        initDrawer(getSelfView().getBinding().llyDrawer);
        initContent(getSelfView().getBinding().llyContent);
    }

    public abstract void initHeader(ViewGroup container);

    public abstract void initDrawer(ViewGroup container);

    public abstract void initContent(ViewGroup container);

    @Override
    public int getLayoutId() {
        return R.layout.include_drawer_layout;
    }

    public void closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START, true);
    }

    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START, true);
    }

    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(getSelfView().getBinding().llyDrawer)) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
