package io.mindjet.jetdemo.viewmodel.item;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;

import java.util.Random;

import io.mindjet.jetdemo.R;
import io.mindjet.jetdemo.databinding.ItemEntryBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;
import rx.functions.Action0;

/**
 * Created by Jet on 5/11/17.
 */

public class EntryItemViewModel extends BaseViewModel<ViewInterface<ItemEntryBinding>> {

    private int name;
    private Action0 onClick;

    public EntryItemViewModel(int name, Action0 onClick) {
        this.name = name;
        this.onClick = onClick;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_entry;
    }

    public String getName() {
        return getString(name);
    }

    @Override
    public void onViewAttached(View view) {
//        Drawable origin = getContext().getResources().getDrawable(R.drawable.shape_point).mutate();
//        getSelfView().getBinding().ivPoint.setImageDrawable(tintDrawable(origin));
    }

//    private Drawable tintDrawable(Drawable drawable) {
//        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            wrappedDrawable.setTint(randomColor());
//        }
//        return wrappedDrawable;
//    }
//
//    private int randomColor() {
//        Random random = new Random();
//        return 0xff000000 | random.nextInt(0x00ffffff);
//    }

    public void onClick() {
        if (onClick != null) onClick.call();
    }

}
