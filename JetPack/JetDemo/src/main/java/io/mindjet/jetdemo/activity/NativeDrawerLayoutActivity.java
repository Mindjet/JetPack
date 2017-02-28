package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.viewmodel.NativeDrawerLayoutDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelCompatActivity;
import io.mindjet.jetgear.mvvm.viewmodel.drawer.DrawerHeaderViewModel;

/**
 * Created by Jet on 2/28/17.
 */

public class NativeDrawerLayoutActivity extends ViewModelCompatActivity<NativeDrawerLayoutDemoViewModel> {

    public static Intent intentFor(Context context) {
        return new Intent(context, NativeDrawerLayoutActivity.class);
    }

    @Override
    public NativeDrawerLayoutDemoViewModel giveMeViewModel() {
        DrawerHeaderViewModel drawerHeaderViewModel = new DrawerHeaderViewModel.Builder()
                .height(R.dimen.drawer_header_height_large)
                .background("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488450208&di=13042111be2919023a3fc158f254edb3&imgtype=jpg&er=1&src=http%3A%2F%2Fimg1.mydrivers.com%2Fimg%2F20131107%2F38c61c2a3dc44cd79c79f6305d237357.jpg")
                .content("Drawer Header Test")
                .icon("https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg")
                .build();
        return new NativeDrawerLayoutDemoViewModel(R.menu.menu_drawer, drawerHeaderViewModel);
    }

    @Override
    public void onViewAttached(NativeDrawerLayoutDemoViewModel viewModel) {

    }
}
