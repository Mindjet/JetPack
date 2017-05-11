package io.mindjet.jetdemo.viewmodel;

import android.support.v4.widget.SwipeRefreshLayout;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeSwipeRecyclerViewBinding;
import io.mindjet.jetgear.databinding.ItemButtonBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.item.ButtonViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.list.SwipeRecyclerViewModel;
import io.mindjet.jetutil.task.Task;

/**
 * Created by Jet on 3/2/17.
 */

public class SwipeRecyclerDemoViewModel extends SwipeRecyclerViewModel<ItemButtonBinding, ActivityInterface<IncludeSwipeRecyclerViewBinding>> {

    private int count = 2;

    @Override
    public void onRefresh() {
        Task.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getAdapter().clear();
                getAdapter().notifyDataSetChanged();
                for (int i = 0; i < count; i++) {
                    getAdapter().add(new ButtonViewModel.Builder().simpleAttr().build());
                }
                getAdapter().notifyItemRangeInserted(0, count);
                getAdapter().onFinishLoadMore(false);
                hideRefreshing();
                count++;
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        Task.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getAdapter().add(new ButtonViewModel.Builder().simpleAttr().build());
                getAdapter().add(new ButtonViewModel.Builder().simpleAttr().build());
                getAdapter().notifyItemRangeInserted(getAdapter().size() - 2, 2);
                getAdapter().onFinishLoadMore(getAdapter().size() >= 20);
            }
        }, 1000);
    }

    @Override
    protected void afterViewAttached() {
        changePbColor(R.color.colorPrimary, R.color.colorAccent);
        getSwipeLayout().setSize(SwipeRefreshLayout.DEFAULT);
    }
}
