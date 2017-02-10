package io.mindjet.jetimage.loader;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Jet on 2/7/17.
 */

public class ImageLoader {

    public static void load(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    public static void load(ImageView imageView, String url, boolean fitCenter) {
        if (fitCenter) {
            load(imageView, url);
        } else {
            Glide.with(imageView.getContext())
                    .load(url)
                    .centerCrop()
                    .into(imageView);
        }
    }

    /**
     * Load image with blur filter.
     *
     * @param imageView the ImageView
     * @param url       the target url
     * @param level     the level of the blur filter, from 0~25.
     */
    public static void loadWithBlur(ImageView imageView, String url, int level) {
        Glide.with(imageView.getContext())
                .load(url)
                .bitmapTransform(new BlurTransformation(imageView.getContext(), level))
                .into(imageView);
    }

    /**
     * Load image with corner.
     *
     * @param imageView the ImageView
     * @param url       the target url
     * @param radius    the corner radius
     */
    public static void loadWithRadius(ImageView imageView, String url, int radius) {
        Glide.with(imageView.getContext())
                .load(url)
                .bitmapTransform(new RoundedCornersTransformation(imageView.getContext(), radius, 0))
                .into(imageView);
    }

    /**
     * Load image and display it in circle framework.
     *
     * @param imageView the ImageView
     * @param url       the target url
     */
    public static void loadCircle(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .bitmapTransform(new CropCircleTransformation(imageView.getContext()))
                .into(imageView);
    }

    /**
     * Load image and display it in square framework.
     *
     * @param imageView the ImageView
     * @param url       the target url
     */
    public static void loadSquare(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .bitmapTransform(new CropSquareTransformation(imageView.getContext()))
                .into(imageView);
    }

}
