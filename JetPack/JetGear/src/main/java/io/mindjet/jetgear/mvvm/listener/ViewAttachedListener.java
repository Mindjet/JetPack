package io.mindjet.jetgear.mvvm.listener;

import io.mindjet.jetgear.mvvm.base.BaseViewModel;

/**
 * Listener called when a view model is attached to its view.
 * <p>
 * Created by Jet on 2/22/17.
 */

public interface ViewAttachedListener<T extends BaseViewModel> {
    void onViewAttached(T viewModel);
}
