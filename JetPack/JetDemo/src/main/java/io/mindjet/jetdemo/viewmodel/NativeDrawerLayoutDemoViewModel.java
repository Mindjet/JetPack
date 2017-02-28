package io.mindjet.jetdemo.viewmodel;

import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeNativeDrawerLayoutBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.NativeDrawerLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.IHeaderItemCallback;

/**
 * Created by Jet on 2/28/17.
 */

public class NativeDrawerLayoutDemoViewModel extends NativeDrawerLayoutViewModel<ActivityInterface<IncludeNativeDrawerLayoutBinding>> {

    public NativeDrawerLayoutDemoViewModel(@MenuRes int menu, BaseViewModel drawerHeaderViewModel) {
        super(menu, drawerHeaderViewModel);
    }

    public NativeDrawerLayoutDemoViewModel() {
    }

    public NativeDrawerLayoutDemoViewModel(@MenuRes int menu, @LayoutRes int drawerHeader) {
        super(menu, drawerHeader);
    }

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
                .centerViewModel(new HeaderItemViewModel.TitleItemViewModel("Native Drawer Layout"))
                .withElevation(true)
                .background(R.color.colorPrimary)
                .build();
        ViewModelBinder.bind(container, headerViewModel);
    }

    @Override
    public void initContent(ViewGroup container) {

    }
}
