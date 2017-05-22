package io.mindjet.jetdemo.activity;

import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityLoadingViewBinding;

/**
 * Loading View Demo.
 * <p>
 * Created by Mindjet on 5/22/17.
 */

public class LoadingViewActivity extends BaseDemoActivity {

    private ActivityLoadingViewBinding mBinding;

    @Override
    public void beforeInitView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_loading_view);
        mBinding.setData(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
