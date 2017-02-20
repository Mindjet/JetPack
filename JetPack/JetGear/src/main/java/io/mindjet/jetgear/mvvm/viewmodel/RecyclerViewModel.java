package io.mindjet.jetgear.mvvm.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeRecyclerViewBinding;
import io.mindjet.jetgear.mvvm.adapter.ViewModelAdapter;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.listener.LoadMoreListener;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;

/**
 * Created by Jet on 2/17/17.
 */

public class RecyclerViewModel<V extends ViewDataBinding> extends BaseViewModel<ViewInterface<IncludeRecyclerViewBinding>> implements LoadMoreListener {

    private Context context;
    private ViewModelAdapter<V> adapter;
    private RecyclerView recyclerView;

    public RecyclerViewModel(Context context) {
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_recycler_view;
    }

    @Override
    public void onViewAttached(View view) {
        recyclerView = getSelfView().getBinding().recyclerView;
        initRecyclerView();
    }

    /**
     * Initialize the RecyclerView with a specific LayoutManager, this method can be overridden to change the LayoutManager.
     */
    protected void initRecyclerView() {
        getRecyclerView().setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onLoadMore() {

    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public ViewModelAdapter<V> getAdapter() {
        if (adapter == null) {
            adapter = new ViewModelAdapter<>(context);
            adapter.setLoadMoreListener(this);
        }
        return adapter;
    }

    @BindingAdapter("android:adapter")
    public static void initAdapter(RecyclerView recyclerView, ViewModelAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

}
