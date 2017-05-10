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

    @Override
    public void onRefresh() {
        Task.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getAdapter().clear();
                getAdapter().add(new ButtonViewModel.Builder().simpleAttr().build());
                getAdapter().add(new ButtonViewModel.Builder().simpleAttr().build());
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
        changePbColor(R.color.colorPrimary, R.color.colorAccent);
        getSwipeLayout().setSize(SwipeRefreshLayout.DEFAULT);
    }
}
