package io.mindjet.jetgear.mvvm.viewmodel.coordinator;

import android.content.res.ColorStateList;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeCoordinatorTabLayoutBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetutil.version.VersionUtil;

/**
 * A view model based on {@link android.support.design.widget.CoordinatorLayout}, consisting of {@link TabLayout} and {@link FloatingActionButton}.
 * <p>
 * Created by Mindjet on 3/1/17.
 */

public abstract class CoordinatorTabLayoutViewModel<V extends ViewInterface<IncludeCoordinatorTabLayoutBinding>> extends BaseViewModel<V> implements TabLayout.OnTabSelectedListener {

    private FloatingActionButton fab;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onViewAttached(View view) {
        fab = getSelfView().getBinding().fab;
        viewPager = getSelfView().getBinding().viewPager;
        tabLayout = getSelfView().getBinding().tabLayout;
        tabLayout.addOnTabSelectedListener(this);
        initDummyStatusbar(getSelfView().getBinding().dummyStatusBar);
        initHeader(getSelfView().getBinding().flyHeader);
        initTab(tabLayout);
        initViewPager(viewPager);
        initFab(fab);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_coordinator_tab_layout;
    }

    /**
     * To have a better user interface, we adopt the Theme called AppTheme.NoTitle, and if your version is before API 21, this no-title feature won't take effect, which means that you still have your original status bar.
     * When the version is API 21 or later, however, this status bar exists and you need to set its background tuned with the style of the header.
     *
     * @param dummyStatusBar the dummy status bar.
     */
    protected abstract void initDummyStatusbar(View dummyStatusBar);

    public abstract void initHeader(ViewGroup container);

    /**
     * You can custom the {@link TabLayout} here with:
     * <ul>
     * <li>{@link #setTabTextColors(int, int)}</li>
     * <li>{@link #setTabBackground(int)} </li>
     * <li>{@link #setIndicatorColor(int)}  </li>
     * </ul>
     *
     * @param tabLayout tab layout.
     */
    public abstract void initTab(TabLayout tabLayout);

    /**
     * You can bind the adapter to the view pager here.
     *
     * @param viewPager view pager.
     */
    public abstract void initViewPager(ViewPager viewPager);

    public ViewPager getViewPager() {
        return viewPager;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public boolean getDummyStatusbarVisibility() {
        return VersionUtil.afterLollipop();
    }

    /**
     * You can custom the {@link FloatingActionButton} here with:
     * <ul>
     * <li>change the fab size ({@link FloatingActionButton#setSize(int)})</li>
     * <li>change the fab icon ({@link FloatingActionButton#setImageResource(int)})</li>
     * <li>change the fab background color ({@link FloatingActionButton#setBackgroundTintList(ColorStateList)} (int)})</li>
     * </ul>
     */
    protected abstract void initFab(FloatingActionButton fab);

    public void hideFab() {
        fab.hide();
    }

    public void showFab() {
        fab.show();
    }

    protected void setTabTextColors(@ColorRes int normalColor, @ColorRes int selectedColor) {
        tabLayout.setTabTextColors(getContext().getResources().getColor(normalColor), getContext().getResources().getColor(selectedColor));
    }

    protected void setTabBackground(@ColorRes int background) {
        tabLayout.setBackgroundColor(getContext().getResources().getColor(background));
    }

    protected void setIndicatorColor(@ColorRes int indicatorColor) {
        tabLayout.setSelectedTabIndicatorColor(getContext().getResources().getColor(indicatorColor));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * Callback of the floating action button.
     */
    public View.OnClickListener getFabClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFabClick();
            }
        };
    }

    /**
     * Called when the {@link FloatingActionButton} is clicked.
     */
    protected abstract void onFabClick();

}
