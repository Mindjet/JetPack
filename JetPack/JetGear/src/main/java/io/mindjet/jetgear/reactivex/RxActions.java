package io.mindjet.jetgear.reactivex;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import io.mindjet.jetgear.mvvm.adapter.ViewModelAdapter;
import io.mindjet.jetgear.mvvm.viewmodel.list.SwipeRecyclerViewModel;
import io.mindjet.jetutil.logger.JLogger;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;

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

    public static <T> Func1<List<T>, Observable<T>> flatMap() {
        return new Func1<List<T>, Observable<T>>() {
            @Override
            public Observable<T> call(List<T> t) {
                return Observable.from(t);
            }
        };
    }

    public static Action0 hideRefreshing(final SwipeRefreshLayout swipe) {
        return new Action0() {
            @Override
            public void call() {
                swipe.setRefreshing(false);
            }
        };
    }

    public static <T> Action1<T> clearAdapter(final ViewModelAdapter adapter, final boolean clear) {
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
