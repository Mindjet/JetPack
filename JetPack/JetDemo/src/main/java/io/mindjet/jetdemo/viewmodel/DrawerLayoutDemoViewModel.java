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
import io.mindjet.jetgear.mvvm.viewmodel.item.ButtonViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;

/**
 * Created by Jet on 2/24/17.
 */

public class DrawerLayoutDemoViewModel extends DrawerLayoutViewModel<ActivityInterface<IncludeDrawerLayoutBinding>> {

    @Override
    public void initHeader(ViewGroup container) {
        HeaderViewModel headerViewModel = new HeaderViewModel.Builder()
                .expendToStatusBar(true)
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
        for (int i = 0; i < 5; i++) {
            recyclerViewModel.getAdapter().add(new ButtonViewModel.Builder().simpleAttr().build());
        }
        recyclerViewModel.getAdapter().notifyItemRangeInserted(0, 5);
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
