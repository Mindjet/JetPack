package io.mindjet.jetgear.mvvm.viewmodel;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.IncludeRecyclerViewBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;

/**
 * Created by Jet on 2/17/17.
 */

public class RecyclerViewModel extends BaseViewModel<ViewInterface<IncludeRecyclerViewBinding>> {


    @Override
    public int getLayoutId() {
        return R.layout.include_recycler_view;
    }
}
