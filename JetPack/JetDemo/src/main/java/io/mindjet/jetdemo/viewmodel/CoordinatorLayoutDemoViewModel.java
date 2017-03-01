package io.mindjet.jetdemo.viewmodel;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import io.mindjet.jetgear.adapter.ViewPagerAdapter;
import io.mindjet.jetgear.databinding.IncludeCoordinatorLayoutBinding;
import io.mindjet.jetgear.databinding.ItemImageTextBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ImageTextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CoordinatorLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetutil.hint.Toaster;

/**
 * Created by Jet on 3/1/17.
 */

public class CoordinatorLayoutDemoViewModel extends CoordinatorLayoutViewModel<ActivityInterface<IncludeCoordinatorLayoutBinding>> {

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

    }

    @Override
    public void initViewPager(ViewPager viewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter();
        viewPager.setAdapter(adapter);
        adapter.addWithTitle(new ImageTextViewModel(), "AAA");
        adapter.addWithTitle(new ImageTextViewModel(), "AAAB");
        adapter.addWithTitle(new ImageTextViewModel(), "AAABB");
        adapter.addWithTitle(new ImageTextViewModel(), "AAABBB");
        adapter.addWithTitle(new ImageTextViewModel(), "AAABBBC");
        getTabLayout().setupWithViewPager(viewPager);
    }

    @Override
    public void initFab(FloatingActionButton fab) {
        fab.setImageResource(io.mindjet.jetgear.R.drawable.ic_starred);
        fab.setSize(FloatingActionButton.SIZE_NORMAL);
    }

    @Override
    public void fabAction() {
        Toaster.toast(getContext(), "Fab is clicked.");
    }

    private class TabViewPagerAdapter extends ViewPagerAdapter<ImageTextViewModel> {

        @Override
        protected Object initItem(ViewGroup container, ImageTextViewModel item, String title, int position) {
            ItemImageTextBinding binding = ViewModelBinder.bind(container, item);
            return binding.getRoot();
        }

    }

}
