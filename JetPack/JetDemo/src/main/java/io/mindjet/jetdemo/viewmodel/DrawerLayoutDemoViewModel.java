package io.mindjet.jetdemo.viewmodel;

import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeDrawerLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ImageTextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.RecyclerViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerHeaderViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerItemViewModel;
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
        DrawerViewModel drawerViewModel = new DrawerViewModel.Builder()
                .item(new DrawerHeaderViewModel.Builder()
                        .height(R.dimen.drawer_header_height)
                        .background("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488450208&di=13042111be2919023a3fc158f254edb3&imgtype=jpg&er=1&src=http%3A%2F%2Fimg1.mydrivers.com%2Fimg%2F20131107%2F38c61c2a3dc44cd79c79f6305d237357.jpg")
                        .content("Drawer Header Test")
                        .icon("https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg")
                        .build())
                .item(new DrawerItemViewModel().content("Inbox").icon(R.drawable.ic_inbox))
                .item(new DrawerItemViewModel().content("Starred").icon(R.drawable.ic_starred))
                .item(new DrawerItemViewModel().content("Sent mails").icon(R.drawable.ic_sent))
                .item(new DrawerItemViewModel().content("Draft").icon(R.drawable.ic_draft))
                .background(R.color.white)
                .width(R.dimen.drawer_width_normal)
                .build();
        ViewModelBinder.bind(container, drawerViewModel);
    }

    @Override
    public void initContent(ViewGroup container) {
        RecyclerViewModel recyclerViewModel = new RecyclerViewModel();
        ViewModelBinder.bind(container, recyclerViewModel);
        recyclerViewModel.getAdapter().disableLoadMore();
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
        recyclerViewModel.getAdapter().add(new ImageTextViewModel());
    }

}
