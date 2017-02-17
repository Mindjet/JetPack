package io.mindjet.jetgear.mvvm.viewmodel;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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

    private int count = 1;

    public RecyclerViewModel(Context context) {
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_recycler_view;
    }

    @Override
    public void onViewAttached(ViewGroup container) {
        setRecyclerView(getSelfView().getBinding().recyclerView);
        initAdapter();
        initRecyclerView();
    }

    private void initAdapter() {
        adapter = new ViewModelAdapter<>(context);
        adapter.setLoadMoreListener(this);
    }

    private void initRecyclerView() {
        getRecyclerView().setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        getRecyclerView().setAdapter(adapter);
    }

    @Override
    public void LoadMore() {
        if (count <= 10) {
            getAdapter().add(new ImageTextViewModel());
            count += 1;
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public ViewModelAdapter<V> getAdapter() {
        return adapter;
    }
}
