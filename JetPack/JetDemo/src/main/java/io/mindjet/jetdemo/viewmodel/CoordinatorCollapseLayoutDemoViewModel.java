package io.mindjet.jetdemo.viewmodel;

import android.support.design.widget.CollapsingToolbarLayout;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeCoordinatorCollapseLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityCompatInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CollapsingImageViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.CoordinatorCollapseLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.item.ImageTextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;
import io.mindjet.jetutil.hint.Toaster;
import io.mindjet.jetwidget.JToolBar;

/**
 * Created by Jet on 3/6/17.
 */

public class CoordinatorCollapseLayoutDemoViewModel extends CoordinatorCollapseLayoutViewModel<ActivityCompatInterface<IncludeCoordinatorCollapseLayoutBinding>> {

    @Override
    protected void afterViewAttached() {
        getSelfView().getActivity().setSupportActionBar(getSelfView().getBinding().toolbar);
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
        toolbar.setNavIcon(R.drawable.ic_drawer);
//        toolbar.setClickable(true);
        toolbar.setNavigationOnClickListener(getNavIconListener());
    }

    @Override
    protected void initContent(ViewGroup container) {
        RecyclerViewModel recyclerViewModel = new RecyclerViewModel();
        ViewModelBinder.bind(container, recyclerViewModel);
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
    }

    @Override
    public boolean createOptionMenu(Menu menu) {
        return false;
    }

    @Override
    protected void onNavIconClick(View view) {
        Toaster.toast(getContext(), "Nav icon clicked!");
    }

}