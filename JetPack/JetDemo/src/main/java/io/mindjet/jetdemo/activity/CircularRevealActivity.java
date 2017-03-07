package io.mindjet.jetdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.ImageView;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ActivityCircularRevealBinding;
import io.mindjet.jetutil.view.AnimUtil;

/**
 * Created by Jet on 3/7/17.
 */

public class CircularRevealActivity extends BaseDemoActivity {

    private ActivityCircularRevealBinding binding;
    private ImageView imageView;

    public static Intent intentFor(Context context) {
        return new Intent(context, CircularRevealActivity.class);
    }

    @Override
    public void beforeInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_circular_reveal);
        imageView = binding.image;
    }

    @Override
    public void initView() {
        binding.center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getVisibility() == View.VISIBLE) {
                    AnimUtil.concealCenter(imageView, 1000);
                } else {
                    AnimUtil.revealCenter(imageView, 1000);
                }
            }
        });
        binding.leftTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getVisibility() == View.VISIBLE) {
                    AnimUtil.concealLeftTop(imageView, 1000);
                } else {
                    AnimUtil.revealLeftTop(imageView, 1000);
                }
            }
        });
        binding.leftBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getVisibility() == View.VISIBLE) {
                    AnimUtil.concealLeftBottom(imageView, 1000);
                } else {
                    AnimUtil.revealLeftBottom(imageView, 1000);
                }
            }
        });
        binding.rightTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getVisibility() == View.VISIBLE) {
                    AnimUtil.concealRightTop(imageView, 1000);
                } else {
                    AnimUtil.revealRightTop(imageView, 1000);
                }
            }
        });
        binding.rightBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getVisibility() == View.VISIBLE) {
                    AnimUtil.concealRightBottom(imageView, 1000);
                } else {
                    AnimUtil.revealRightBottom(imageView, 1000);
                }
            }
        });
    }

    @Override
    public void initData() {

    }

}
