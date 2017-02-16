package io.mindjet.jetgear.mvvm.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * Created by Jet on 2/10/17.
 */

public abstract class BaseAdapter<V extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder<V>> implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

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
        onBindVH(holder, position);
        holder.getBinding().executePendingBindings();
    }

    public abstract void onBindVH(BaseViewHolder<V> holder, int position);

    @Override
    public int getItemViewType(int position) {
        return getItemLayoutId(position);
    }

    public abstract int getItemLayoutId(int position);

    public LayoutInflater getInflater() {
        return inflater;
    }
}
