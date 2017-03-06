package io.mindjet.jetgear.mvvm.viewmodel.item;

import android.view.View;

import java.util.UUID;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemTextCardviewBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;

/**
 * Created by Mindjet on 2017/3/6.
 */

public class TextCardViewModel extends BaseViewModel<ViewInterface<ItemTextCardviewBinding>> {

    private String text = UUID.randomUUID().toString();
    private String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488450197&di=a151ec971c05b732bd97e7a5d836a4e2&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.xiazaizhijia.com%2Fuploads%2Fallimg%2F150926%2F195-15092615352MT.jpg";

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void onViewAttached(View view) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.item_text_cardview;
    }
}
