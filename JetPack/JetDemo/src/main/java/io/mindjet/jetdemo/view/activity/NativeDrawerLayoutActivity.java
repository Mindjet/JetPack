package io.mindjet.jetdemo.view.activity;

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
                .background("http://pic.qiantucdn.com/58pic/19/74/39/571093256279c_1024.jpg!/watermark/url/L3dhdGVybWFyay12MS4zLnBuZw==/align/center")
                .content("Native Drawer")
                .icon("https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg")
                .build();
        return new NativeDrawerLayoutDemoViewModel(R.menu.menu_drawer, drawerHeaderViewModel);
    }

    @Override
    public void onViewAttached(NativeDrawerLayoutDemoViewModel viewModel) {

    }
}
