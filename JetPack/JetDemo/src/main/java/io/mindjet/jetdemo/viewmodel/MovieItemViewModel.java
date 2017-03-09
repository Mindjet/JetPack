package io.mindjet.jetdemo.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ItemMovieBinding;
import io.mindjet.jetdemo.model.MovieItem;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.AdapterInterface;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class MovieItemViewModel extends BaseViewModel<AdapterInterface<ItemMovieBinding>> {

    public ObservableField<String> image;
    public ObservableField<String> name;
    public ObservableField<String> subtitle;
    public ObservableField<String> rating;

    public MovieItemViewModel(MovieItem item) {
        image = new ObservableField<>(item.getImage());
        name = new ObservableField<>(item.getName());
        subtitle = new ObservableField<>(item.getSubtitle());
        rating = new ObservableField<>(item.getRating());
    }

    @Override
    public void onViewAttached(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_movie;
    }
}
