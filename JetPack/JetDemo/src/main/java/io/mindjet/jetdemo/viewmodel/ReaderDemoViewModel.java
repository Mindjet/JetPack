package io.mindjet.jetdemo.viewmodel;

import android.databinding.ViewDataBinding;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.adapter.ViewPagerAdapter;
import io.mindjet.jetgear.databinding.IncludeDrawerCoordinatorLayoutBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerHeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.IHeaderItemCallback;
import io.mindjet.jetgear.mvvm.viewmodel.integrated.DrawerCoordinatorLayoutViewModel;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ReaderDemoViewModel extends DrawerCoordinatorLayoutViewModel<ActivityInterface<IncludeDrawerCoordinatorLayoutBinding>> {

    @Override
    protected void afterViewAttached(IncludeDrawerCoordinatorLayoutBinding binding) {

    }

    @Override
    protected void initDummyStatusBar(View dummyStatusBar) {
        dummyStatusBar.setBackgroundColor(getContext().getResources().getColor(R.color.zhihu_color));
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
                .centerViewModel(new HeaderItemViewModel.TitleItemViewModel("轻阅读"))
                .background(R.color.zhihu_color)
                .sink(false)
                .withElevation(false)
                .build();
        ViewModelBinder.bind(container, vm);
    }

    @Override
    protected void initTabLayout(TabLayout tabLayout) {
        tabLayout.setBackgroundColor(getContext().getResources().getColor(R.color.zhihu_color));
        tabLayout.setTabTextColors(getContext().getResources().getColor(R.color.white), getContext().getResources().getColor(R.color.white));
        tabLayout.setSelectedTabIndicatorColor(getContext().getResources().getColor(R.color.white));
    }

    @Override
    protected void initDrawer(ViewGroup container) {
        DrawerViewModel viewModel = new DrawerViewModel.Builder()
                .width(R.dimen.drawer_width_normal)
                .background(R.color.white)
                .item(new DrawerHeaderViewModel.Builder()
                        .content("DRAWER COORDINATOR")
                        .icon("https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg")
                        .build())
                .item(new DrawerItemViewModel().icon(R.drawable.ic_draft_gray))
                .item(new DrawerItemViewModel().icon(R.drawable.ic_sent_gray))
                .item(new DrawerItemViewModel().icon(R.drawable.ic_starred_gray))
                .item(new DrawerItemViewModel().icon(R.drawable.ic_inbox_gray))
                .build();
        ViewModelBinder.bind(container, viewModel);
    }

    @Override
    protected void initFab(FloatingActionButton fab) {
        fab.setVisibility(View.GONE);
    }

    @Override
    protected void initViewPager(ViewPager viewPager) {
        ZZAdapter adapter = new ZZAdapter();
        viewPager.setAdapter(adapter);
        adapter.addWithTitle(new ArticleListViewModel(), "知乎日报");
        adapter.addWithTitle(new MovieListViewModel(), "最新影片");
    }

    @Override
    protected void onFabClick() {

    }

    private class ZZAdapter extends ViewPagerAdapter<BaseViewModel> {

        @Override
        protected Object initItem(ViewGroup container, BaseViewModel item, String title, int position) {
            ViewDataBinding binding = ViewModelBinder.bind(container, item);
            return binding.getRoot();
        }
    }

}
