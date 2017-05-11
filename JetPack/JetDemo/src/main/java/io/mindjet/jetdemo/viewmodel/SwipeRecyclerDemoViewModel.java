package io.mindjet.jetdemo.viewmodel;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeSwipeRecyclerViewBinding;
import io.mindjet.jetgear.databinding.ItemButtonBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityInterface;
import io.mindjet.jetgear.mvvm.viewmodel.item.ButtonViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.list.SwipeRecyclerViewModel;
import io.mindjet.jetgear.reactivex.RxActions;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jet on 3/2/17.
 */

public class SwipeRecyclerDemoViewModel extends SwipeRecyclerViewModel<ItemButtonBinding, ActivityInterface<IncludeSwipeRecyclerViewBinding>> {

    private int index = 0;

    @Override
    public void onRefresh() {
        index = 0;
        addItems();
    }

    @Override
    public void onLoadMore() {
        addItems();
    }

    private void addItems() {
        Observable.just("")
                .repeat(3)
                .observeOn(Schedulers.io())
                .map(new Func1<String, ButtonViewModel>() {
                    @Override
                    public ButtonViewModel call(String s) {
                        return new ButtonViewModel.Builder().simpleAttr().build();
                    }
                })
                .toList()
                .delay(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(RxActions.clearAdapter(getAdapter(), index == 0))
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .doOnUnsubscribe(RxActions.hideRefreshing(getSwipeLayout()))
                .subscribe(new Action1<List<ButtonViewModel>>() {
                    @Override
                    public void call(List<ButtonViewModel> list) {
                        getAdapter().addAll(list);
                        getAdapter().notifyItemRangeInserted(index, list.size());
                        index += list.size();
                        getAdapter().onFinishLoadMore(list.size() == 0);
                    }
                }, RxActions.onError());
    }

    @Override
    protected void afterViewAttached() {
        changePbColor(R.color.colorPrimary, R.color.colorAccent);
        getSwipeLayout().setSize(SwipeRefreshLayout.DEFAULT);
    }
}
