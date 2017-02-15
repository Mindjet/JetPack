package io.mindjet.jetgear.mvvm.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.mindjet.jetgear.BR;

/**
 * Created by Jet on 2/10/17.
 */

public abstract class BaseAdapter<V extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder<V>> {

    private LayoutInflater inflater;

    public BaseAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new BaseViewHolder<V>(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<V> holder, int position) {
        holder.getBinding().setVariable(BR.data, holder.getViewModel());
        holder.getBinding().executePendingBindings();
        afterBindViewHolder();
    }

    public abstract void afterBindViewHolder();

}
