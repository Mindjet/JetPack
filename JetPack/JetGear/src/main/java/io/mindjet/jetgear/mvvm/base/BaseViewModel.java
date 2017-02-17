package io.mindjet.jetgear.mvvm.base;

import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import io.mindjet.jetgear.BR;
import io.mindjet.jetgear.mvvm.ILayoutId;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;

/**
 * Created by Jet on 2/15/17.
 */

public abstract class BaseViewModel<V extends ViewInterface> implements ILayoutId {

    private V selfView;

    /**
     * This method is called after the view has been attached to the container.
     *
     * @param selfView  the view who represents the ViewModel itself and maintains binding to the resource layout file.
     * @param container the container contains the ViewModel.
     */
    public void onAttach(V selfView, ViewGroup container) {
        this.selfView = selfView;
        attachData(selfView.getBinding());
        onViewAttached(container);
    }

    /**
     * This method attaches data to the layout file.
     * <p>
     * The name of the variable is <strong>"data"</strong> generally.
     *
     * @param binding the binding of the layout resource file.
     */
    private void attachData(ViewDataBinding binding) {
        binding.setVariable(BR.data, this);
    }

    /**
     * This method is called after the ViewModel has been bound to the container.
     *
     * @param container the container contains the ViewModel.
     */
    public void onViewAttached(ViewGroup container) {

    }

    /**
     * Return the view who represents the ViewModel and maintains the binding to the corresponding resource layout file.
     */
    public V getSelfView() {
        return selfView;
    }

}
