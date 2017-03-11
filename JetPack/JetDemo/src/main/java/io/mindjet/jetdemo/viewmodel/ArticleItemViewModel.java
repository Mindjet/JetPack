package io.mindjet.jetdemo.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ItemArticleBinding;
import io.mindjet.jetdemo.model.ArticleItem;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.AdapterInterface;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ArticleItemViewModel extends BaseViewModel<AdapterInterface<ItemArticleBinding>> {

    public ObservableField<String> image;
    public ObservableField<String> title;

    public ArticleItemViewModel(ArticleItem articleItem) {
        image = new ObservableField<>(articleItem.getImages().get(0));
        title = new ObservableField<>(articleItem.getTitle());
    }

    @Override
    public void onViewAttached(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_article;
    }
}
