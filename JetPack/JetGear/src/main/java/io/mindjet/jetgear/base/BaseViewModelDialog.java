package io.mindjet.jetgear.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.view.ViewGroup;
import android.view.Window;

import io.mindjet.jetgear.BR;
import io.mindjet.jetutil.logger.JLogger;

/**
 * Base dialog to bind view model.
 * <p>
 * Created by Mindjet on 5/10/17.
 */

public abstract class BaseViewModelDialog<V extends ViewDataBinding> extends AppCompatDialog {

    protected JLogger jLogger = JLogger.get(getClass().getSimpleName());
    protected V binding;

    public BaseViewModelDialog(@NonNull Context context) {
        super(context);
        beforeInitView();
        afterViewAttached(binding);
    }

    private void beforeInitView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), null, false);
        setContentView(binding.getRoot());
        binding.setVariable(BR.data, this);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void afterViewAttached(V binding);


}
