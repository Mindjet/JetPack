package io.mindjet.jetgear.mvvm.viewinterface;

import android.databinding.ViewDataBinding;

import io.mindjet.jetgear.mvvm.adapter.LoadMoreAdapter;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;

/**
 * Created by Jet on 2/17/17.
 */

public interface AdapterInterface<T extends BaseViewModel, V extends ViewDataBinding> extends ViewInterface<V> {

    LoadMoreAdapter<T, V> getAdapter();

}
