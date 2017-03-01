package io.mindjet.jetgear.mvvm.viewmodel.coordinator;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeCoordinatorLayoutBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.IHeaderItemCallback;

/**
 * Created by Jet on 3/1/17.
 */

public class CoordinatorLayoutViewModel extends BaseViewModel<ActivityInterface<IncludeCoordinatorLayoutBinding>> {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    public void onViewAttached(View view) {
        drawerLayout = getSelfView().getBinding().drawerLayout;
        navigationView = getSelfView().getBinding().navigationView;
        initHeader(getSelfView().getBinding().flyHeader);
        initTab(getSelfView().getBinding().tabLayout);
        initViewPager(getSelfView().getBinding().viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_coordinator_layout;
    }

    private void initHeader(ViewGroup container) {
        HeaderViewModel headerViewModel = new HeaderViewModel.Builder()
                .leftViewModel(new HeaderItemViewModel()
                        .icon(R.drawable.ic_drawer)
                        .callback(new IHeaderItemCallback() {
                            @Override
                            public void call() {
                                toggleDrawer();
                            }
                        }))
                .withElevation(false)
                .centerViewModel(new HeaderItemViewModel.TitleItemViewModel("Coordinator Layout"))
                .build();
        ViewModelBinder.bind(container, headerViewModel);
    }

    public void initTab(TabLayout tabLayout) {

    }

    private void initViewPager(ViewPager viewPager) {

    }

    public void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();
        } else {
            drawerLayout.openDrawer(GravityCompat.START, true);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();
        } else {
            getSelfView().getActivity().finish();
        }
    }
}
