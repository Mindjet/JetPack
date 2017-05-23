package io.mindjet.jetwidget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.mindjet.jetutil.drawable.DrawableDyer;

/**
 * A cute dialog.
 * <p>
 * Created by Mindjet on 5/23/17.
 */

public class QLoadingDialog extends Dialog {

    private QLoadingDialog.Builder mBuilder;

    private LinearLayout mLinearLayout;
    private QLoadingView mLoadingView;
    private TextView mTextView;

    private QLoadingDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.CuteDialog);
        mBuilder = builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_cute_loading);
        mLinearLayout = (LinearLayout) findViewById(R.id.lly_container);
        mLoadingView = ((QLoadingView) findViewById(R.id.loading_view));
        mTextView = ((TextView) findViewById(R.id.text_view));

        //dye the background of container.
        Drawable output = DrawableDyer.dye(mLinearLayout.getBackground(), getContext().getResources().getColor(mBuilder.backgroundColorRes));
        mLinearLayout.setBackground(output);

        mLoadingView.setBallColor(getContext().getResources().getColor(mBuilder.loadingViewColorRes));
        mTextView.setText(mBuilder.contentRes == 0 ? mBuilder.content : getContext().getResources().getString(mBuilder.contentRes));
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(mBuilder.textSize));
        mTextView.setTextColor(getContext().getResources().getColor(mBuilder.contentColorRes));
        mTextView.setGravity(mBuilder.gravity);

        setCancelable(mBuilder.cancelable);
        setCanceledOnTouchOutside(mBuilder.cancelable);
    }

    public static class Builder {
        private Context context;
        @ColorRes
        private int backgroundColorRes = R.color.white;
        @ColorRes
        private int loadingViewColorRes = R.color.colorAccent;
        @ColorRes
        private int contentColorRes = R.color.black;
        @DimenRes
        private int textSize = R.dimen.common_text_size;
        @StringRes
        private int contentRes;
        private String content = "";
        private int gravity = Gravity.START;
        private boolean cancelable = true;

        public Builder(Context context) {
            this.context = context;
        }

        public QLoadingDialog.Builder backgroundColorRes(@ColorRes int backgroundColorRes) {
            this.backgroundColorRes = backgroundColorRes;
            return this;
        }

        public QLoadingDialog.Builder loadingViewColorRes(@ColorRes int loadingViewColorRes) {
            this.loadingViewColorRes = loadingViewColorRes;
            return this;
        }

        public QLoadingDialog.Builder contentColorRes(@ColorRes int contentColorRes) {
            this.contentColorRes = contentColorRes;
            return this;
        }

        public QLoadingDialog.Builder textSize(@DimenRes int textSize) {
            this.textSize = textSize;
            return this;
        }

        public QLoadingDialog.Builder content(@StringRes int contentRes) {
            this.contentRes = contentRes;
            return this;
        }

        public QLoadingDialog.Builder content(String content) {
            this.content = content;
            return this;
        }

        public QLoadingDialog.Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public QLoadingDialog.Builder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public QLoadingDialog build() {
            return new QLoadingDialog(context, this);
        }

    }

}
