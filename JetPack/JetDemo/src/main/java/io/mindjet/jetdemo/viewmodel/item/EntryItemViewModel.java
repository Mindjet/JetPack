package io.mindjet.jetdemo.viewmodel.item;

import android.view.View;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ItemEntryBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import rx.functions.Action0;

/**
 * Created by Jet on 5/11/17.
 */

public class EntryItemViewModel extends BaseViewModel<ViewInterface<ItemEntryBinding>> {

    private int name;
    private Action0 onClick;

    public EntryItemViewModel(int name, Action0 onClick) {
        this.name = name;
        this.onClick = onClick;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_entry;
    }

    public String getName() {
        return getString(name);
    }

    @Override
    public void onViewAttached(View view) {
//        getSelfView().getBinding().ivPoint.setImageDrawable(DrawableDyer.dyeRandomColor(getContext().getResources().getDrawable(R.drawable.shape_point)));
    }


    public void onClick() {
        if (onClick != null) onClick.call();
    }

}
