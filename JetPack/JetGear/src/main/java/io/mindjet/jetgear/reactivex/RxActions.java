package io.mindjet.jetgear.reactivex;

import io.mindjet.jetgear.mvvm.adapter.ViewModelAdapter;
import io.mindjet.jetutil.logger.JLogger;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;

/**
 * Simple actions for RxJava.
 * <p>
 * Created by Mindjet on 2017/4/11.
 */

public class RxActions {

    private static JLogger jLogger = JLogger.get("RxActions");

    public static Action1<Throwable> onError() {
        return new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                jLogger.e(throwable);
            }
        };
    }

    public static <T> Action1<T> clear(final ViewModelAdapter adapter, final boolean clear) {
        return Actions.toAction1(new Action0() {
            @Override
            public void call() {
                if (clear) {
                    adapter.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}
