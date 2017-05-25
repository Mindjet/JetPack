package io.mindjet.jetdemo.view.activity.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import io.mindjet.jetdemo.BR;

/**
 * Base activity for data binding.
 * <p>
 * Created by Mindjet on 5/25/17.
 */

public abstract class BaseBindingDemoActivity<V extends ViewDataBinding> extends BaseDemoActivity {

    protected V mBinding;

    protected abstract int getLayoutId();

    @Override
    public void beforeInitView() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mBinding.setVariable(BR.data, this);
    }

}
