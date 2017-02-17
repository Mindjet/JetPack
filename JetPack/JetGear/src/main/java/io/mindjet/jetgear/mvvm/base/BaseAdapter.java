package io.mindjet.jetgear.mvvm.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.mvvm.listener.RcvItemClickListener;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetutil.logger.JLogger;

/**
 * Created by Jet on 2/10/17.
 */

public abstract class BaseAdapter<V extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder> implements RcvItemClickListener, View.OnClickListener, View.OnLongClickListener {

    private LayoutInflater inflater;
    private JLogger jLogger = JLogger.get(getClass().getSimpleName());

    public BaseAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        binding.getRoot().setOnClickListener(this);
        binding.getRoot().setOnLongClickListener(this);
        return new BaseViewHolder<ViewInterface<V>>(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        onBindVH(holder, position);
        holder.itemView.setTag(holder);
        holder.getBinding().executePendingBindings();
    }

    public abstract void onBindVH(BaseViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {
        return getItemLayoutId(position);
    }

    public abstract int getItemLayoutId(int position);

    public LayoutInflater getInflater() {
        return inflater;
    }

    @Override
    public void onClick(View v) {
        BaseViewHolder holder = ((BaseViewHolder) v.getTag());
        onItemClick(holder.getBinding(), holder.getLayoutPosition());
        jLogger.e(holder.getLayoutPosition());
    }

    @Override
    public boolean onLongClick(View v) {
        BaseViewHolder holder = ((BaseViewHolder) v.getTag());
        onItemLongClick(holder.getBinding(), holder.getLayoutPosition());
        return true;
    }
}
