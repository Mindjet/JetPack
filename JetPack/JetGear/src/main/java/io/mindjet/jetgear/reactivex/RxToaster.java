package io.mindjet.jetgear.reactivex;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;

import io.mindjet.jetutil.hint.Toaster;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;

/**
 * A Toast helper for RxJava flow structure.
 * <p>
 * Created by Mindjet on 5/11/17.
 */

public class RxToaster {

    @UiThread
    public static Action0 toastAction0(final Context context, final String content) {
        return new Action0() {
            @Override
            public void call() {
                Toaster.toast(context, content);
            }
        };
    }

    @UiThread
    public static <T> Action1<T> toastAction1(Context context, String content) {
        return Actions.toAction1(toastAction0(context, content));
    }

    @UiThread
    public static Action0 toastAction0(final Context context, @StringRes final int stringRes) {
        return new Action0() {
            @Override
            public void call() {
                Toaster.toast(context, stringRes);
            }
        };
    }

    @UiThread
    public static <T> Action1<T> toastAction1(Context context, @StringRes int stringRes) {
        return Actions.toAction1(toastAction0(context, stringRes));
    }

}
