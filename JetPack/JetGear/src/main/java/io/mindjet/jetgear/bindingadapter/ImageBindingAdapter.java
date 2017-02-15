package io.mindjet.jetgear.bindingadapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import io.mindjet.jetimage.loader.ImageLoader;

/**
 * Created by Mindjet on 2017/2/15.
 */

public class ImageBindingAdapter {

    @BindingAdapter("android:src")
    public static void ImageSrc(ImageView imageview, String src) {
        ImageLoader.load(imageview, src);
    }

    @BindingAdapter("android:url")
    public static void ImageUrl(ImageView imageView, String url) {
        ImageLoader.load(imageView, url);
    }

}
