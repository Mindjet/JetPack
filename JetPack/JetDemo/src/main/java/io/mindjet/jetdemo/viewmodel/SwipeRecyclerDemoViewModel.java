package io.mindjet.jetdemo.viewmodel;

import android.support.v4.widget.SwipeRefreshLayout;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeSwipeRecyclerViewBinding;
import io.mindjet.jetgear.databinding.ItemImageTextBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.item.ImageTextViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.list.SwipeRecyclerViewModel;
import io.mindjet.jetutil.task.Task;

/**
 * Created by Jet on 3/2/17.
 */

public class SwipeRecyclerDemoViewModel extends SwipeRecyclerViewModel<ItemImageTextBinding, ActivityInterface<IncludeSwipeRecyclerViewBinding>> {

    @Override
    public void onRefresh() {
        Task.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getAdapter().clear();
                getAdapter().add(new ImageTextViewModel());
                getAdapter().add(new ImageTextViewModel());
                getAdapter().notifyDataSetChanged();
                hideRefreshing();
            }
        }, 3000);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    protected void afterViewAttached() {
        getAdapter().disableLoadMore();
        changePbColor(R.color.colorPrimary, R.color.colorAccent);
        changePbBackground(R.color.chocolate);
        getSwipeLayout().setSize(SwipeRefreshLayout.DEFAULT);
    }
}
