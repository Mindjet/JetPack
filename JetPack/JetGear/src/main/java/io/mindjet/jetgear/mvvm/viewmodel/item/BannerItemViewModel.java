package io.mindjet.jetgear.mvvm.viewmodel.item;

import android.view.View;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemBannerBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import io.mindjet.jetutil.hint.Toaster;

/**
 * Banner item view model.
 * <p>
 * Created by Mindjet on 5/15/17.
 */

public class BannerItemViewModel extends BaseViewModel<ViewInterface<ItemBannerBinding>> {

    private String url;

    public BannerItemViewModel(String url) {
        this.url = url;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_banner;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void onViewAttached(View view) {

    }

    public void onClick() {
        Toaster.toast(getContext(), url);
    }

}
