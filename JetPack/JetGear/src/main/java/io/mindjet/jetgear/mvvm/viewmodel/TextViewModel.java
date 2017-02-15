package io.mindjet.jetgear.mvvm.viewmodel;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemTextBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;

/**
 * Created by Mindjet on 2017/2/15.
 */

public class TextViewModel extends BaseViewModel<ItemTextBinding> {

    private String text;

    public TextViewModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_text;
    }
}
