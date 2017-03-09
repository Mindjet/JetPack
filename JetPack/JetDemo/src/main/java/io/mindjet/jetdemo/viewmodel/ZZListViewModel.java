package io.mindjet.jetdemo.viewmodel;

import android.support.v7.widget.StaggeredGridLayoutManager;

import io.mindjet.jetdemo.databinding.ItemZzBinding;
import io.mindjet.jetdemo.model.ZZ;
import io.mindjet.jetdemo.model.ZZItem;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;
import io.mindjet.jetgear.network.ServiceGen;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ZZListViewModel extends RecyclerViewModel<ItemZzBinding> {

    private GithubService service;

    public ZZListViewModel() {
        service = ServiceGen.create(GithubService.class);
    }

    @Override
    protected void initRecyclerView() {
        getRecyclerView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onLoadMore() {
        service.getZZ()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ZZ>() {
                    @Override
                    public void call(ZZ zz) {
                        for (ZZItem zzItem : zz.getStories()) {
                            getAdapter().add(new ZZItemViewModel(zzItem));
                        }
                        getAdapter().notifyDataSetChanged();
                        getAdapter().finishLoadMore(true);
                    }
                });
    }
}
