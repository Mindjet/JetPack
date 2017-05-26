package io.mindjet.jetdemo.viewmodel;

import io.mindjet.jetdemo.R;
import io.mindjet.jetgear.databinding.IncludeSandwichLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityCompatInterface;
import io.mindjet.jetgear.mvvm.viewmodel.sandwich.SandwichWebViewModel;

/**
 * Created by Mindjet on 5/26/17.
 */

public class SandwichWebViewDemoViewModel extends SandwichWebViewModel<ActivityCompatInterface<IncludeSandwichLayoutBinding>> {

    @Override
    protected int getMainColor() {
        return getContext().getResources().getColor(R.color.colorPrimary);
    }

}
