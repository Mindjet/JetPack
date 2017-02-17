package io.mindjet.jetgear.mvvm.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemProgressBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewHolder;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.listener.LoadMoreListener;
import io.mindjet.jetutil.logger.JLogger;

/**
 * Created by Jet on 2/16/17.
 */

public abstract class LoadMoreAdapter<T extends BaseViewModel, V extends ViewDataBinding> extends ListAdapter<T, V> implements LoadMoreListener {

    private JLogger jLogger = JLogger.get(getClass().getSimpleName());
    private boolean loadMore = true;
    private ItemProgressBinding progressBinding;

    public LoadMoreAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return loadMore ? size() + 1 : size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (loadMore && viewType == R.layout.item_progress) {
            progressBinding = ItemProgressBinding.inflate(getInflater(), parent, false);
            return new BaseViewHolder<>(progressBinding);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindVH(BaseViewHolder holder, int position) {
        if (loadMore && position == size()) {
            holder.getBinding().getRoot().setVisibility(View.VISIBLE);
            onLoadMore();
        } else {
            super.onBindVH(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (loadMore && position == getItemCount() - 1) {
            return R.layout.item_progress;
        }
        return super.getItemViewType(position);
    }

    public void enableLoadMore() {
        loadMore = true;
    }

    public void disableLoadMore() {
        loadMore = false;
    }

    public void finishLoadMore(boolean lastPage) {
        progressBinding.getRoot().setVisibility(View.GONE);
        if (lastPage) loadMore = false;
    }

    @Override
    public void LoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoadMore();
            }
        }, 500);
    }

    public abstract void onLoadMore();

}
