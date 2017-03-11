package io.mindjet.jetdemo.viewmodel;

import android.content.res.ColorStateList;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeCoordinatorCollapseLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityCompatInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CollapsingImageViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CoordinatorCollapseLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.item.ImageTextCardViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;
import io.mindjet.jetutil.hint.Toaster;
import io.mindjet.jetutil.task.Task;
import io.mindjet.jetutil.view.AnimUtil;
import io.mindjet.jetwidget.JToolBar;

/**
 * Created by Jet on 3/6/17.
 */

public class CoordinatorCollapseLayoutDemoViewModel extends CoordinatorCollapseLayoutViewModel<ActivityCompatInterface<IncludeCoordinatorCollapseLayoutBinding>> {

    @Override
    protected void afterViewAttached() {
        getSelfView().getCompatActivity().setSupportActionBar(getSelfView().getBinding().toolbar);
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
        CollapsingImageViewModel vm = new CollapsingImageViewModel("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2077732093,2100397646&fm=21&gp=0.jpg");
        ViewModelBinder.bind(container, vm);
    }

    @Override
    protected void initToolbar(JToolBar toolbar) {
        toolbar.setNavIcon(R.drawable.ic_left_arrow);
    }

    @Override
    protected void initFab(FloatingActionButton fab) {
        fab.setVisibility(View.INVISIBLE);
        fab.setSize(FloatingActionButton.SIZE_AUTO);
        fab.setImageResource(R.drawable.ic_drawer);
        fab.setBackgroundTintList(ColorStateList.valueOf(getContext().getResources().getColor(R.color.colorPrimary)));
        //Need to wrap in a runnable as the view is not attached right now.
        Task.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AnimUtil.revealCenter(getFab(), 600);
            }
        });
    }

    @Override
    protected void initContent(ViewGroup container) {
        RecyclerViewModel recyclerViewModel = new RecyclerViewModel();
        ViewModelBinder.bind(container, recyclerViewModel);
        recyclerViewModel.getRecyclerView().setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewModel.getAdapter().disableLoadMore();
        recyclerViewModel.getAdapter().add(new ImageTextCardViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextCardViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextCardViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextCardViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextCardViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextCardViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextCardViewModel());
    }

    @Override
    protected void onNavigationIconClick() {
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
