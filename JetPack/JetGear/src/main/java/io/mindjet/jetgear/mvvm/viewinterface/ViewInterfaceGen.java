package io.mindjet.jetgear.mvvm.viewinterface;

import android.content.Context;
import android.databinding.ViewDataBinding;

import io.mindjet.jetgear.mvvm.adapter.ViewModelAdapter;
import io.mindjet.jetgear.mvvm.base.BaseViewHolder;

/**
 * Created by Jet on 2/17/17.
 */

public class ViewInterfaceGen {

    /**
     * Generate a ViewInterface with a given binding.
     *
     * @param binding the binding
     * @param <V>     the type of the binding.
     * @return a ViewInterface with a context and a binding.
     */
    public static <V extends ViewDataBinding> ViewInterface<V> viewInterface(final V binding) {
        return new ViewInterface<V>() {
            @Override
            public Context getContext() {
                return binding.getRoot().getContext();
            }

            @Override
            public V getBinding() {
                return binding;
            }
        };
    }

    /**
     * Generate a AdapterInterface with ViewModelAdapter and ViewHolder.
     *
     * @param adapter
     * @param viewHolder
     * @param <V>
     * @return a AdapterInterface with a ViewModelAdapter, BaseViewHolder, context and binding.
     */
    public static <V extends ViewDataBinding> AdapterInterface<V> adapterInterface(final ViewModelAdapter<V> adapter, final BaseViewHolder<V> viewHolder) {
        return new AdapterInterface<V>() {
            @Override
            public ViewModelAdapter<V> getAdapter() {
                return adapter;
            }

            @Override
            public BaseViewHolder<V> getViewHolder() {
                return viewHolder;
            }

            @Override
            public Context getContext() {
                return adapter.getContext();
            }

            @Override
            public V getBinding() {
                return viewHolder.getBinding();
            }
        };
    }


}
