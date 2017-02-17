package io.mindjet.jetgear.mvvm.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.mindjet.jetgear.BR;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;

/**
 * Created by Jet on 2/10/17.
 */

public class BaseViewHolder<V extends ViewInterface> extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;
    private BaseViewModel<V> viewModel;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object o) {
        binding.setVariable(BR.data, o);
        binding.executePendingBindings();
    }

    public BaseViewModel<V> getViewModel() {
        return viewModel;
    }

    public void setViewModel(BaseViewModel<V> viewModel) {
        this.viewModel = viewModel;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

}
