package io.mindjet.jetdemo.viewmodel;

import android.content.res.ColorStateList;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeCoordinatorCollapseLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityCompatInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CollapsingImageViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CoordinatorCollapseLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.item.ButtonViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;
import io.mindjet.jetutil.anim.RevealUtil;
import io.mindjet.jetutil.hint.Toaster;
import io.mindjet.jetwidget.JToolBar;

/**
 * Created by Jet on 3/6/17.
 */

public class CoordinatorCollapseLayoutDemoViewModel extends CoordinatorCollapseLayoutViewModel<ActivityCompatInterface<IncludeCoordinatorCollapseLayoutBinding>> {

    @Override
    protected void afterViewAttached() {
        getSelfView().getCompatActivity().setSupportActionBar(getSelfView().getBinding().toolbar);
        RevealUtil.revealActivity(getSelfView().getCompatActivity(), 500, 100, 100);
    }

    @Override
    protected void initCollapsingToolbar(CollapsingToolbarLayout layout) {
        layout.setTitle("Collapsing Toolbar");
        layout.setContentScrimResource(R.color.colorPrimary);
        layout.setExpandedTitleMarginBottom(getContext().getResources().getDimensionPixelSize(R.dimen.common_gap_large));
        layout.setExpandedTitleMarginStart(getContext().getResources().getDimensionPixelSize(R.dimen.common_gap_large));
        layout.setExpandedTitleTextAppearance(R.style.ToolbarTextAppearance_Expanded);
        layout.setCollapsedTitleTextAppearance(R.style.ToolbarTextAppearance_Collapsed);
    }

    @Override
    protected void initCollapsingContent(ViewGroup container) {
        ViewModelBinder.bind(container, new CollapsingImageViewModel());
    }

    @Override
    protected void setNavIcon(JToolBar toolbar) {
        toolbar.setNavIcon(R.drawable.ic_arrow_left);
    }

    @Override
    protected void initFab(FloatingActionButton fab) {
        fab.setSize(FloatingActionButton.SIZE_AUTO);
        fab.setImageResource(R.drawable.ic_drawer);
        fab.setBackgroundTintList(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorPrimary)));
    }

    @Override
    protected void initContent(ViewGroup container) {
        RecyclerViewModel recyclerViewModel = new RecyclerViewModel();
        ViewModelBinder.bind(container, recyclerViewModel);
        recyclerViewModel.getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 2));
        for (int i = 0; i < 5; i++) {
            recyclerViewModel.getAdapter().add(new ButtonViewModel.Builder().simpleAttr().build());
        }
        recyclerViewModel.getAdapter().notifyItemRangeInserted(0, 5);
    }

    @Override
    protected void setSupportActionBar(JToolBar toolBar) {
        getSelfView().getCompatActivity().setSupportActionBar(toolBar);
    }

    @Override
    protected void onNavIconClick() {
        getSelfView().getCompatActivity().finish();
    }

    @Override
    protected void onFabClick() {
        Toaster.toast(getContext(), "Floating action button clicked!");
    }

    @Override
    public boolean onCreateOptionMenu(Menu menu) {
        getSelfView().getCompatActivity().getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toaster.toast(getContext(), item.getTitle().toString() + " item clicked");
        return true;
    }

}
