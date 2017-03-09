package io.mindjet.jetdemo.viewmodel;

import android.databinding.ObservableField;
import android.view.View;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ItemZzBinding;
import io.mindjet.jetdemo.model.ZZItem;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.AdapterInterface;

/**
 * Created by Mindjet on 2017/3/9.
 */

public class ZZItemViewModel extends BaseViewModel<AdapterInterface<ItemZzBinding>> {

    public ObservableField<String> image;
    public ObservableField<String> title;

    public ZZItemViewModel(ZZItem zzItem) {
        image = new ObservableField<>(zzItem.getImages().get(0));
        title = new ObservableField<>(zzItem.getTitle());
    }

    @Override
    public void onViewAttached(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_zz;
    }
}
