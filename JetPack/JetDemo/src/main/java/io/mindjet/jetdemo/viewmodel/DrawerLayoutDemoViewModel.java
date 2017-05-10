package io.mindjet.jetdemo.viewmodel;

import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.util.DrawerViewModelGen;
import io.mindjet.jetgear.databinding.IncludeDrawerLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.IHeaderItemCallback;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;

/**
 * Created by Jet on 2/24/17.
 */

public class DrawerLayoutDemoViewModel extends DrawerLayoutViewModel<ActivityInterface<IncludeDrawerLayoutBinding>> {

    @Override
    public void initHeader(ViewGroup container) {
        HeaderViewModel headerViewModel = new HeaderViewModel.Builder()
                .sink(true)
                .leftViewModel(new HeaderItemViewModel()
                        .icon(io.mindjet.jetgear.R.drawable.ic_drawer)
                        .clickable(true)
                        .callback(new IHeaderItemCallback() {
                            @Override
                            public void call() {
                                toggleDrawer();
                            }
                        }))
                .centerViewModel(new HeaderItemViewModel.TitleItemViewModel("Drawer Layout"))
                .background(R.color.colorPrimary)
                .build();
        ViewModelBinder.bind(container, headerViewModel);
    }

    @Override
    public void initDrawer(ViewGroup container) {
        ViewModelBinder.bind(container, DrawerViewModelGen.create());
    }

    @Override
    public void initContent(ViewGroup container) {
        RecyclerViewModel recyclerViewModel = new RecyclerViewModel();
        ViewModelBinder.bind(container, recyclerViewModel);
//        recyclerViewModel.getAdapter().disableLoadMore(); // TODO: 5/10/17 API更新
//        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
//        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
//        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
//        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
    }

    @Override
    public boolean onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
            return true;
        }
        return false;
    }
}
