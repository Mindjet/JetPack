package io.mindjet.jetgear.mvvm.viewmodel;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemImageTextBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;

/**
 * Created by Mindjet on 2017/2/15.
 */

public class ImageTextViewModel extends BaseViewModel<ItemImageTextBinding> {

    private String imageUrl = "https://imgsa.baidu.com/forum/w%3D580/sign=9a8f6a0f9545d688a302b2ac94c27dab/ca67d5a20cf431ad929de0054c36acaf2fdd988b.jpg";
    private String title = "Mindjet";
    private String content = "Get credit for all your work by showing the number of contributions to private repositories on your profile without any repository or organization information. Learn how we count contributions.";

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_image_text;
    }
}
