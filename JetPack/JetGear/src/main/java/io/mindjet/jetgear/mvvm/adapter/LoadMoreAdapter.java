package io.mindjet.jetgear.mvvm.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemProgressBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewHolder;
import io.mindjet.jetgear.mvvm.listener.LoadMoreListener;

/**
 * Load more adapter for RecyclerView.
 * <p>
 * Created by Mindjet on 2/16/17.
 */

public abstract class LoadMoreAdapter<T, V extends ViewDataBinding> extends ListAdapter<T, V> {

    public LoadMoreListener loadMoreListener;
    private boolean loadMore = false;
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
            jLogger.e("on create loading view holder");
            progressBinding = ItemProgressBinding.inflate(getInflater(), parent, false);
            return new BaseViewHolder<>(progressBinding);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<V> holder, int position) {
        if (loadMore && position == size()) {
            jLogger.e("on bind view holder load more");
            holder.getBinding().getRoot().setVisibility(View.VISIBLE);
            loadMore();
        } else {
            super.onBindViewHolder(holder, position);
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

    public void onFinishLoadMore(boolean lastPage) {
        if (progressBinding != null)
            progressBinding.getRoot().setVisibility(View.GONE);
        if (lastPage) {
            if (loadMore) {
                disableLoadMore();
                notifyItemRemoved(getItemCount());
            }
        } else {
            jLogger.e("on finish load more");
            enableLoadMore();
            notifyItemInserted(getItemCount());
        }
    }

    private void loadMore() {
        if (loadMoreListener != null) {
            jLogger.e("inside load more method");
            loadMoreListener.onLoadMore();
        }
    }

}
