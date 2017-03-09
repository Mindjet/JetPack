package io.mindjet.jetdemo.viewmodel;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetdemo.Constant;
import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.activity.FollowerDetailActivity;
import io.mindjet.jetdemo.listener.IFollowerListener;
import io.mindjet.jetgear.adapter.ViewPagerAdapter;
import io.mindjet.jetgear.databinding.IncludeDrawerCoordinatorLayoutBinding;
import io.mindjet.jetgear.databinding.IncludeRecyclerViewBinding;
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
 * Created by Jet on 3/9/17.
 */

public class DrawerCoordinatorLayoutDemoViewModel extends DrawerCoordinatorLayoutViewModel<ActivityInterface<IncludeDrawerCoordinatorLayoutBinding>> implements IFollowerListener {

    @Override
    protected void afterViewAttached(IncludeDrawerCoordinatorLayoutBinding binding) {

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
                .sink(false)
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
        DrawerViewModel viewModel = new DrawerViewModel.Builder()
                .width(R.dimen.drawer_width_normal)
                .background(R.color.white)
                .item(new DrawerHeaderViewModel.Builder()
                        .content("DRAWER COORDINATOR")
                        .icon("https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg")
                        .build())
                .item(new DrawerItemViewModel().icon(R.drawable.ic_draft))
                .item(new DrawerItemViewModel().icon(R.drawable.ic_sent))
                .item(new DrawerItemViewModel().icon(R.drawable.ic_starred))
                .item(new DrawerItemViewModel().icon(R.drawable.ic_inbox))
                .build();
        ViewModelBinder.bind(container, viewModel);
    }

    @Override
    protected void initFab(FloatingActionButton fab) {
        fab.setImageResource(R.drawable.ic_drawer);
    }

    @Override
    protected void initViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter();
        viewPager.setAdapter(adapter);
        adapter.addWithTitle(new GithubFollowerListViewModel("liuny05").callback(this), "Jake Wharton");
        adapter.addWithTitle(new GithubFollowerListViewModel("Mindjet").callback(this), "Mindjet");
        adapter.addWithTitle(new GithubFollowerListViewModel("Gringe920").callback(this), "Gringe");
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void onFabClick() {

    }

    @Override
    public void onFollowerClick(int position, GithubFollowerViewModel item) {
        Pair<View, String> pairImage = new Pair<>((View) item.getSelfView().getBinding().ivAvatar, getContext().getResources().getString(R.string.transition_name_image));
        Pair<View, String> pairText = new Pair<>((View) item.getSelfView().getBinding().tvName, getContext().getResources().getString(R.string.transition_name_text));
        ActivityOptionsCompat option = ActivityOptionsCompat.makeSceneTransitionAnimation(getSelfView().getActivity(), pairImage, pairText);
        Intent intent = FollowerDetailActivity.intentFor(getContext());
        intent.putExtra(Constant.INTENT_AVATAR, item.avatarUrl.get());
        intent.putExtra(Constant.INTENT_NAME, item.name.get());
        getSelfView().getActivity().startActivity(intent, option.toBundle());
    }

    private class Adapter extends ViewPagerAdapter<GithubFollowerListViewModel> {
        @Override
        protected Object initItem(ViewGroup container, GithubFollowerListViewModel item, String title, int position) {
            IncludeRecyclerViewBinding binding = ViewModelBinder.bind(container, item);
            return binding.getRoot();
        }
    }

}
