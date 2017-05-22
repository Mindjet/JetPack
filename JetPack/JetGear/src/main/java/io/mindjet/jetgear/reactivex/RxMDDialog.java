package io.mindjet.jetgear.reactivex;

import android.content.Context;
import android.support.annotation.StringRes;

import io.mindjet.jetwidget.MDDialog;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;

/**
 * A MDDialog for RxJava flow structure.
 * <p>
 * Created Mindjet Jet on 5/11/17.
 */

public class RxMDDialog {

    public static Action0 showAction0(final Context context, @StringRes final int stringRes) {
        return new Action0() {
            @Override
            public void call() {
                MDDialog.show(context, stringRes);
            }
        };
    }

    public static Action0 dismiss() {
        return new Action0() {
            @Override
            public void call() {
                MDDialog.dismiss();
            }
        };
    }

    public static <T> Action1<T> showAction1(Context context, @StringRes int stringRes) {
        return Actions.toAction1(showAction0(context, stringRes));
    }

}
