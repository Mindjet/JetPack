package io.mindjet.jetgear.mvvm.viewmodel.sandwich;

import android.view.View;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemSandwichWebHeaderBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;

/**
 * Header view model for sandwich webview model.
 * <p>
 * Created by Mindjet on 5/26/17.
 */

public class SandwichWebHeaderViewModel extends BaseViewModel<ViewInterface<ItemSandwichWebHeaderBinding>> {

    @Override
    public int getLayoutId() {
        return R.layout.item_sandwich_web_header;
    }

    @Override
    public void onViewAttached(View view) {

    }
}
