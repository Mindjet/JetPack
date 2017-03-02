package io.mindjet.jetgear.mvvm.viewmodel.coordinator;

import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeCoordinatorCollapseLayoutBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.item.ImageTextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;

/**
 * Created by Jet on 3/2/17.
 */

public class CoordinatorCollapseLayoutViewModel<V extends ViewInterface<IncludeCoordinatorCollapseLayoutBinding>> extends BaseViewModel<V> {

    @Override
    public void onViewAttached(View view) {
        initContent(getSelfView().getBinding().llyContainer);
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_coordinator_collapse_layout;
    }

    private void initHeader(ViewGroup container) {
        HeaderViewModel headerViewModel = new HeaderViewModel.Builder()
                .leftViewModel(new HeaderItemViewModel.TitleItemViewModel("Collapse Layout"))
                .background(R.color.colorPrimary)
                .build();
        ViewModelBinder.bind(container, headerViewModel);
    }

    private void initContent(ViewGroup container) {
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

}
