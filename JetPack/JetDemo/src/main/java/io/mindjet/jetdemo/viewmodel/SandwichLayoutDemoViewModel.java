package io.mindjet.jetdemo.viewmodel;

import android.view.View;
import android.view.ViewGroup;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.viewmodel.item.SimpleViewModel;
import io.mindjet.jetgear.databinding.IncludeSandwichLayoutBinding;
import io.mindjet.jetgear.mvvm.viewinterface.ActivityCompatInterface;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.coordinator.SandwichLayoutViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.item.ButtonViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.list.RecyclerViewModel;

/**
 * Created by Mindjet on 5/26/17.
 */

public class SandwichLayoutDemoViewModel extends SandwichLayoutViewModel<ActivityCompatInterface<IncludeSandwichLayoutBinding>> {

    private RecyclerViewModel mRecyclerViewModel;

    @Override
    protected void initDummyStatusbarStyle(View dummyStatusbar) {
        dummyStatusbar.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void initSandwichTop(ViewGroup container) {
        SimpleViewModel vm = new SimpleViewModel.Builder()
                .backgroundRes(R.color.colorPrimary)
                .textColor(R.color.white)
                .content("Sandwich Top")
                .build();
        ViewModelBinder.bind(container, vm);
    }

    @Override
    protected void initSandwichMiddle(ViewGroup container) {
        mRecyclerViewModel = new RecyclerViewModel(true);
        ViewModelBinder.bind(container, mRecyclerViewModel);
    }

    @Override
    protected void initSandwichBottom(ViewGroup container) {
        SimpleViewModel vm = new SimpleViewModel.Builder()
                .backgroundRes(R.color.colorAccent)
                .textColor(R.color.white)
                .content("Sandwich Bottom")
                .build();
        ViewModelBinder.bind(container, vm);
    }

    @Override
    protected void afterComponentsBound(IncludeSandwichLayoutBinding binding) {
        for (int i = 0; i < 20; i++) {
            mRecyclerViewModel.getAdapter().add(new ButtonViewModel.Builder().simpleAttr().build());
        }
        mRecyclerViewModel.getAdapter().notifyItemRangeInserted(0, 20);
    }
}
