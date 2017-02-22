package io.mindjet.jetgear.mvvm.viewmodel;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.mindjet.jetgear.mvvm.adapter.ViewModelAdapter;
import io.mindjet.jetgear.mvvm.base.BaseViewHolder;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewinterface.AdapterInterface;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterfaceGen;

/**
 * Created by Jet on 2/17/17.
 */

public class ViewModelBinder {

    /**
     * This method is used to bind ViewModel to the given container.
     *
     * @param container the container to contains the ViewModel.
     * @param viewModel the target ViewModel.
     * @param <V>       the binding type(extending ViewDataBinding) of the ViewModel.
     */
    public static <V extends ViewDataBinding> void bind(ViewGroup container, BaseViewModel<ViewInterface<V>> viewModel) {
        V binding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), viewModel.getLayoutId(), container, true);
        ViewInterface<V> viewInterface = ViewInterfaceGen.viewInterface(binding);
        viewModel.onAttach(viewInterface);
    }

    /**
     * This method is used to bind ViewModel to the ViewHolder, when the method {@link android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)} is called.
     *
     * @param adapter    the target adapter contains ViewModel.
     * @param viewModel  the target ViewModel to be bound.
     * @param viewHolder the corresponding ViewHolder.
     */
    public static <V extends ViewDataBinding> void bind(ViewModelAdapter<V> adapter, BaseViewModel<AdapterInterface<V>> viewModel, BaseViewHolder<V> viewHolder) {
        AdapterInterface<V> adapterInterface = ViewInterfaceGen.adapterInterface(adapter, viewHolder);
        viewModel.onAttach(adapterInterface);
    }

    /**
     * This method is used to bind ViewModel to Activity, and use ViewModel's layout as Activity's contentView.
     *
     * @param activity  the activity to be bound to.
     * @param viewModel the ViewModel to be bound.
     * @param <V>       the binding type.
     */
    public static <V extends ViewDataBinding> void bind(final Activity activity, BaseViewModel<ActivityInterface<V>> viewModel) {
        final V binding = DataBindingUtil.setContentView(activity, viewModel.getLayoutId());
        ActivityInterface<V> activityInterface = ViewInterfaceGen.activityInterface(activity, binding);
        viewModel.onAttach(activityInterface);
    }


}
