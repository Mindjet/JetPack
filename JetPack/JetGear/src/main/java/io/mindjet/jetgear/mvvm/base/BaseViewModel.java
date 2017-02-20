package io.mindjet.jetgear.mvvm.base;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.mindjet.jetgear.BR;
import io.mindjet.jetgear.mvvm.ILayoutId;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetutil.logger.JLogger;

/**
 * Created by Jet on 2/15/17.
 */

public abstract class BaseViewModel<V extends ViewInterface> extends BaseObservable implements ILayoutId {

    protected JLogger jLogger = JLogger.get(getClass().getSimpleName());
    private V selfView;

    /**
     * This method is called after the view has been attached to the container.
     * And if the ViewModel is RecyclerView item, this method is called when the method {@link android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)} is called.
     *
     * @param selfView the view who represents the ViewModel itself and maintains binding to the resource layout file.
     */
    public void onAttach(V selfView) {
        this.selfView = selfView;
        attachData(selfView.getBinding());
        onViewAttached(selfView.getBinding().getRoot());
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
     * @param view the View represents the ViewModel.
     */
    public abstract void onViewAttached(View view);

    /**
     * Return the view who represents the ViewModel and maintains the binding to the corresponding resource layout file.
     */
    public V getSelfView() {
        return selfView;
    }

    public Context getContext() {
        return getSelfView().getContext();
    }

}
