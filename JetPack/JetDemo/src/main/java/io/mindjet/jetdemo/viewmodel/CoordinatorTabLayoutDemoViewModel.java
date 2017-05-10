package io.mindjet.jetdemo.viewmodel;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.adapter.ViewPagerAdapter;
import io.mindjet.jetgear.databinding.IncludeCoordinatorTabLayoutBinding;
import io.mindjet.jetgear.databinding.ItemButtonBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CoordinatorTabLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.item.ButtonViewModel;
import io.mindjet.jetutil.hint.Toaster;

/**
 * Created by Jet on 3/1/17.
 */

public class CoordinatorTabLayoutDemoViewModel extends CoordinatorTabLayoutViewModel<ActivityInterface<IncludeCoordinatorTabLayoutBinding>> {

    @Override
    protected void initDummyStatusbar(View view) {

    }

    @Override
    public void initHeader(ViewGroup container) {
        HeaderViewModel headerViewModel = new HeaderViewModel.Builder()
                .sink(true)
                .leftViewModel(new HeaderItemViewModel.TitleItemViewModel("Coordinator Layout"))
                .withElevation(false)
                .build();
        ViewModelBinder.bind(container, headerViewModel);
    }

    @Override
    public void initTab(TabLayout tabLayout) {
        setTabTextColors(R.color.white, R.color.colorAccent);
        setIndicatorColor(R.color.colorAccent);
    }

    @Override
    public void initViewPager(ViewPager viewPager) {
        ViewPagerAdapter<ButtonViewModel> adapter = new ViewPagerAdapter<ButtonViewModel>() {
            @Override
            protected Object initItem(ViewGroup container, ButtonViewModel item, String title, int position) {
                return ViewModelBinder.bind(container, item).getRoot();
            }
        };
        viewPager.setAdapter(adapter);
        for (int i = 0; i < 5; i++) {
            adapter.addWithTitle(new ButtonViewModel.Builder().simpleAttr().build(), getString(R.string.app_name));
        }
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
