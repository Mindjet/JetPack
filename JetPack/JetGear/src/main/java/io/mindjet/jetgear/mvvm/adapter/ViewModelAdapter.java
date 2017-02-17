package io.mindjet.jetgear.mvvm.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;

import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.listener.LoadMoreListener;

/**
 * Created by Jet on 2/17/17.
 */

public class ViewModelAdapter<V extends ViewDataBinding> extends LoadMoreAdapter<BaseViewModel, V> {

    private LoadMoreListener loadMoreListener;

    public ViewModelAdapter(Context context) {
        super(context);
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public void onLoadMore() {
        if (loadMoreListener != null) {
            loadMoreListener.LoadMore();
        }
    }
}
