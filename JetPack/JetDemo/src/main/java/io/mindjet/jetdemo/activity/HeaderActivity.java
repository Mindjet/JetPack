package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityHeaderBinding;
import io.mindjet.jetgear.mvvm.viewmodel.ViewModelBinder;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderItemViewModel;
import io.mindjet.jetgear.mvvm.viewmodel.header.HeaderViewModel;

/**
 * Created by Jet on 2/21/17.
 */

public class HeaderActivity extends BaseDemoActivity {

    private ActivityHeaderBinding binding;

    public static Intent intentFor(Context context) {
        return new Intent(context, HeaderActivity.class);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_header);
    }

    @Override
    public void initView() {
        HeaderViewModel headerViewModel = new HeaderViewModel.Builder()
                .background(R.color.white)
                .leftViewModel(new HeaderItemViewModel.BackItemViewModel(this))
                .centerViewModel(new HeaderItemViewModel.TitleItemViewModel("HEADER TEST"))
                .build();
        ViewModelBinder.bind(binding.llyContainer, headerViewModel);
    }

    @Override
    public void initData() {

    }
}
