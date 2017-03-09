package io.mindjet.jetdemo.activity;

import io.mindjet.jetdemo.viewmodel.ZhihuDemoViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.activity.ViewModelActivity;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ZhihuDemoActivity extends ViewModelActivity<ZhihuDemoViewModel> {

    @Override
    public ZhihuDemoViewModel giveMeViewModel() {
        return new ZhihuDemoViewModel();
    }

    @Override
    public void onViewAttached(ZhihuDemoViewModel viewModel) {

    }
}
