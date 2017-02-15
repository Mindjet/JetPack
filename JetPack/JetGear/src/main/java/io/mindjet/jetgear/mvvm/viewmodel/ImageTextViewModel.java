package io.mindjet.jetgear.mvvm.viewmodel;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemImageTextBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;

/**
 * Created by Mindjet on 2017/2/15.
 */

public class ImageTextViewModel extends BaseViewModel<ItemImageTextBinding> {

    private String imageUrl = "https://avatars0.githubusercontent.com/u/17674741?v=3&u=d885cca444cf1881b81076563f49ca4751c675f9&s=400";
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
