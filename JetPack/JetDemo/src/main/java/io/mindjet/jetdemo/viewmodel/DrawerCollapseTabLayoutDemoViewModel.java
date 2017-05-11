package io.mindjet.jetdemo.viewmodel;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.util.DrawerViewModelGen;
import io.mindjet.jetgear.adapter.ViewPagerAdapter;
import io.mindjet.jetgear.databinding.IncludeDrawerCollapseTabLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.IHeaderItemCallback;
import io.mindjet.jetgear.mvvm.viewmodel.integrated.DrawerCollapseTabLayoutViewModel;

/**
 * Created by Jet on 3/9/17.
 */

public class DrawerCollapseTabLayoutDemoViewModel extends DrawerCollapseTabLayoutViewModel<ActivityInterface<IncludeDrawerCollapseTabLayoutBinding>> {

    @Override
    protected void afterViewAttached(IncludeDrawerCollapseTabLayoutBinding binding) {

    }

    @Override
    protected void initDummyStatusBar(View dummyStatusBar) {
        dummyStatusBar.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void initAppBarLayout(AppBarLayout appBarLayout) {

    }

    @Override
    protected void initHeader(ViewGroup container) {
        HeaderViewModel vm = new HeaderViewModel.Builder()
                .leftViewModel(new HeaderItemViewModel()
                        .icon(R.drawable.ic_drawer)
                        .callback(new IHeaderItemCallback() {
                            @Override
                            public void call() {
                                toggleDrawer();
                            }
                        }))
                .background(R.color.colorPrimary)
                .expendToStatusBar(false)
                .withElevation(false)
                .build();
        ViewModelBinder.bind(container, vm);
    }

    @Override
    protected void initTabLayout(TabLayout tabLayout) {
        tabLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
        tabLayout.setTabTextColors(getContext().getResources().getColor(R.color.white), getContext().getResources().getColor(R.color.white));
        tabLayout.setSelectedTabIndicatorColor(getContext().getResources().getColor(R.color.white));
    }

    @Override
    protected void initDrawer(ViewGroup container) {
        ViewModelBinder.bind(container, DrawerViewModelGen.create());
    }

    @Override
    protected void initFab(FloatingActionButton fab) {
        fab.setImageResource(R.drawable.ic_drawer);
    }

    @Override
    protected void initViewPager(ViewPager viewPager) {
        ViewPagerAdapter<GithubFollowerListViewModel> adapter = new ViewPagerAdapter<GithubFollowerListViewModel>() {
            @Override
            protected Object initItem(ViewGroup container, GithubFollowerListViewModel item, String title, int position) {
                return ViewModelBinder.bind(container, item).getRoot();
            }
        };
        viewPager.setAdapter(adapter);
        adapter.addWithTitle(new GithubFollowerListViewModel("Jake Wharton"), "Jake Wharton");
        adapter.addWithTitle(new GithubFollowerListViewModel("Mindjet"), "Mindjet");
        adapter.addWithTitle(new GithubFollowerListViewModel("Gringe920"), "Gringe");
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    protected void onFabClick() {

    }

}
