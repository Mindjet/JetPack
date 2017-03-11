package io.mindjet.jetdemo.viewmodel;

import android.support.v7.widget.StaggeredGridLayoutManager;

import io.mindjet.jetdemo.databinding.ItemArticleBinding;
import io.mindjet.jetdemo.model.ArticleList;
import io.mindjet.jetdemo.model.ArticleItem;
import io.mindjet.jetdemo.service.GithubService;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;
import io.mindjet.jetgear.network.ServiceGen;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ArticleListViewModel extends RecyclerViewModel<ItemArticleBinding> {

    private GithubService service;

    public ArticleListViewModel() {
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
                .subscribe(new Action1<ArticleList>() {
                    @Override
                    public void call(ArticleList articleList) {
                        for (ArticleItem articleItem : articleList.getStories()) {
                            getAdapter().add(new ArticleItemViewModel(articleItem));
                        }
                        getAdapter().notifyDataSetChanged();
                        getAdapter().finishLoadMore(true);
                    }
                });
    }
}
