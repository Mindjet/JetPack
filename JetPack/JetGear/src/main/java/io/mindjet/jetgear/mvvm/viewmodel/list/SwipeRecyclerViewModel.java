package io.mindjet.jetgear.mvvm.viewmodel.list;

import android.databinding.ViewDataBinding;
import android.support.annotation.ColorRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeSwipeRecyclerViewBinding;
import io.mindjet.jetgear.mvvm.adapter.ViewModelAdapter;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.listener.LoadMoreListener;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetutil.task.Task;

/**
 * SwipeRefreshLayout+RecyclerView ViewModel, provides refresh and load more features.
 * <p>
 * Created by Mindjet on 3/2/17.
 */

public class SwipeRecyclerViewModel<S extends ViewDataBinding, V extends ViewInterface<IncludeSwipeRecyclerViewBinding>> extends BaseViewModel<V> implements LoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeLayout;
    private RecyclerViewModel<S> recyclerViewModel;

    @Override
    public void onViewAttached(View view) {
        swipeLayout = getSelfView().getBinding().swipeLayout;
        afterViewAttached();
        initSwipeLayout();
        initRecyclerView();
        afterComponentsBound();

        //auto refresh at the very first time
        swipeLayout.setRefreshing(true);
        Task.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        }, 1000);
    }

    private void initSwipeLayout() {
        swipeLayout.setOnRefreshListener(this);
    }

    @SuppressWarnings("unchecked")
    private void initRecyclerView() {
        recyclerViewModel = new RecyclerViewModel(true);
        recyclerViewModel.setLoadMoreListener(this);
        //一开始不允许load more，虽然进入时不会load more，但是当refresh失败时，如果触碰屏幕就会自动load more，所以应该在refresh成功后才允许load more
        recyclerViewModel.disableLoadMore();
        ViewModelBinder.bind(swipeLayout, recyclerViewModel);
    }

    /**
     * Please call the method after all components are bound. Otherwise, {@link NullPointerException} will be thrown.
     *
     * @return SwipeRefreshLayout.
     */
    protected SwipeRefreshLayout getSwipeLayout() {
        return swipeLayout;
    }

    /**
     * Please call the method after all components are bound. Otherwise, {@link NullPointerException} will be thrown.
     *
     * @return RecyclerView.
     */
    public RecyclerView getRecyclerView() {
        return recyclerViewModel.getRecyclerView();
    }

    /**
     * Please call the method after all components are bound. Otherwise, {@link NullPointerException} will be thrown.
     *
     * @return adapter of the RecyclerView.
     */
    public ViewModelAdapter<S> getAdapter() {
        return recyclerViewModel.getAdapter();
    }

    protected void afterViewAttached() {

    }

    /**
     * All components are safe to manipulate in this method, as all of them are already bound.
     */
    protected void afterComponentsBound() {

    }

    /**
     * @see RecyclerViewModel#setIsLoadingMore(boolean)
     */
    public void setIsLoadingMore(boolean isLoadingMore) {
        recyclerViewModel.setIsLoadingMore(isLoadingMore);
    }

    public void disableLoadMore() {
        recyclerViewModel.disableLoadMore();
    }

    public void enableLoadMore() {
        recyclerViewModel.enableLoadMore();
    }

    @Override
    public int getLayoutId() {
        return R.layout.include_swipe_recycler_view;
    }

    public void hideRefreshing() {
        swipeLayout.setRefreshing(false);
    }

    public void showRefreshing() {
        swipeLayout.setRefreshing(true);
    }

    public void changePbColor(@ColorRes int... draggingColor) {
        swipeLayout.setColorSchemeResources(draggingColor);
    }

    public void changePbBackground(@ColorRes int background) {
        swipeLayout.setProgressBackgroundColorSchemeResource(background);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
