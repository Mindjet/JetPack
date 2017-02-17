package io.mindjet.jetgear.mvvm.viewinterface;

import android.content.Context;
import android.databinding.ViewDataBinding;

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

}
