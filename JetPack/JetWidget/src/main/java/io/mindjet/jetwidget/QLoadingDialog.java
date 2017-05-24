package io.mindjet.jetwidget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

/**
 * A cute dialog.
 * <p>
 * Created by Mindjet on 5/23/17.
 */

public class QLoadingDialog extends Dialog {

    private static final String TAG = "QLoadingDialog";
    private QLoadingDialog.Builder mBuilder;

    private CornerLinearLayout mLinearLayout;
    private QLoadingView mLoadingView;
    private TextView mTextView;

    private QLoadingDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.CuteDialog);
        mBuilder = builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_cute_loading);
        mLinearLayout = (CornerLinearLayout) findViewById(R.id.lly_container);
        mLoadingView = ((QLoadingView) findViewById(R.id.loading_view));
        mTextView = ((TextView) findViewById(R.id.text_view));

        //dye the background of container.
        mLinearLayout.setBackgroundColor(getContext().getResources().getColor(mBuilder.backgroundColorRes));
        mLinearLayout.setCornerRadius(mBuilder.cornerRadius == 0 ? 0 : getContext().getResources().getDimension(mBuilder.cornerRadius));

        mLoadingView.setBallColor(getContext().getResources().getColor(mBuilder.loadingViewColorRes));
        mLoadingView.setEclipsed(mBuilder.loadingBallsEclipsed);

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
        @DimenRes
        private int cornerRadius = 0;
        @StringRes
        private int contentRes;
        private String content = TAG;
        private int gravity = Gravity.START;
        private boolean cancelable = true;
        private boolean loadingBallsEclipsed = false;

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

        public QLoadingDialog.Builder cornerRadius(@DimenRes int cornerRadius) {
            this.cornerRadius = cornerRadius;
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

        public QLoadingDialog.Builder loadingBallsEclipsed(boolean loadingBallsEclipsed) {
            this.loadingBallsEclipsed = loadingBallsEclipsed;
            return this;
        }

        public QLoadingDialog build() {
            return new QLoadingDialog(context, this);
        }

    }

}
