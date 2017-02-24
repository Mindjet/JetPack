package io.mindjet.jetdemo.viewmodel;

import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeDrawerLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ImageTextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.RecyclerViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.TextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.IHeaderItemCallback;

/**
 * Created by Jet on 2/24/17.
 */

public class DrawerLayoutDemoViewModel extends DrawerLayoutViewModel<ActivityInterface<IncludeDrawerLayoutBinding>> {

    @Override
    public void initHeader(ViewGroup container) {
        HeaderViewModel headerViewModel = new HeaderViewModel.Builder()
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
                .background(R.color.white)
                .build();
        ViewModelBinder.bind(container, headerViewModel);
    }

    @Override
    public void initDrawer(ViewGroup container) {
        DrawerViewModel drawerViewModel = new DrawerViewModel.Builder()
                .item(new ImageTextViewModel())
                .item(new TextViewModel("asdasdas"))
                .item(new ImageTextViewModel())
                .item(new TextViewModel("asdasdas"))
                .item(new ImageTextViewModel())
                .item(new TextViewModel("asdasdas"))
                .background(R.color.white)
                .width(R.dimen.drawer_width_normal)
                .build();
        ViewModelBinder.bind(container, drawerViewModel);
    }

    @Override
    public void initContent(ViewGroup container) {

    }

}
