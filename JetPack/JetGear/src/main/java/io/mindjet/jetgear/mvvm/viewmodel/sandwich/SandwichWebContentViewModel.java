package io.mindjet.jetgear.mvvm.viewmodel.sandwich;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import io.mindjet.jetgear.R;
import io.mindjet.jetgear.databinding.ItemSandwichWebContentBinding;
import io.mindjet.jetgear.mvvm.base.BaseViewModel;
import io.mindjet.jetgear.mvvm.viewinterface.ViewInterface;

/**
 * Web view for sandwich webview model as content.
 * <p>
 * Created by Mindjet on 5/26/17.
 */

public class SandwichWebContentViewModel extends BaseViewModel<ViewInterface<ItemSandwichWebContentBinding>> {

    private WebView mWebView;

    @Override
    public int getLayoutId() {
        return R.layout.item_sandwich_web_content;
    }

    @Override
    public void onViewAttached(View view) {
        mWebView = getSelfView().getBinding().webView;
        initWebView();
        mWebView.loadUrl("https://mindjet.github.io/");
    }

    private void initWebView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)          //API 21 之后默认不允许在https/http资源混合加载，要手动开启
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.setLongClickable(false);
        mWebView.setPadding(0, 0, 0, 0);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(true);
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    private class MyChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            jLogger.e(url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            jLogger.e(url);
        }
    }

}
