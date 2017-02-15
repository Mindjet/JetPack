package io.mindjet.jetgear.mvvm.base;

import android.databinding.ViewDataBinding;

import io.mindjet.jetgear.mvvm.ILayoutId;

/**
 * Created by Jet on 2/15/17.
 */

public abstract class BaseViewModel<V extends ViewDataBinding> implements ILayoutId {

    private ViewDataBinding binding;

}
