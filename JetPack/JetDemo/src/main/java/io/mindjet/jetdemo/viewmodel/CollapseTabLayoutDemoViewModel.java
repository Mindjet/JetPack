package io.mindjet.jetdemo.viewmodel;

import android.databinding.ViewDataBinding;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.adapter.ViewPagerAdapter;
import io.mindjet.jetgear.databinding.IncludeCollapseTabLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CollapseTabLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetutil.hint.Toaster;

/**
 * Created by Jet on 3/1/17.
 */

public class CollapseTabLayoutDemoViewModel extends CollapseTabLayoutViewModel<ActivityInterface<IncludeCollapseTabLayoutBinding>> {

    @Override
    protected void initDummyStatusbar(View view) {
        view.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void initHeader(ViewGroup container) {
        HeaderViewModel headerViewModel = new HeaderViewModel.Builder()
                .leftViewModel(new HeaderItemViewModel.TitleItemViewModel("Coordinator Layout"))
                .withElevation(false)
                .build();
        ViewModelBinder.bind(container, headerViewModel);
    }

    @Override
    public void initTab(TabLayout tabLayout) {
        setTabBackground(R.color.colorPrimary);
        setTabTextColors(R.color.white, R.color.white);
        setIndicatorColor(R.color.white);
    }

    @Override
    public void initViewPager(ViewPager viewPager) {
        ViewPagerAdapter<GithubFollowerListViewModel> adapter = new ViewPagerAdapter<GithubFollowerListViewModel>() {
            @Override
            protected Object initItem(ViewGroup container, GithubFollowerListViewModel item, String title, int position) {
                ViewDataBinding binding = ViewModelBinder.bind(container, item);
                return binding.getRoot();
            }
        };
        viewPager.setAdapter(adapter);
        for (int i = 0; i < 3; i++) {
            adapter.addWithTitle(new GithubFollowerListViewModel(), getString(R.string.app_name));
        }
        viewPager.setOffscreenPageLimit(adapter.getCount());
    }

    @Override
    public void initFab(FloatingActionButton fab) {
        fab.setVisibility(View.GONE);
    }

    @Override
    public void onFabClick() {
        Toaster.toast(getContext(), "Fab is clicked.");
    }

}
