package com.zmj.qvod.presenter;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jude.beam.expansion.BeamBasePresenter;
import com.zmj.qvod.view.activity.WebViewActivity;
import com.zmj.qvod.widght.MyWebViewClient;

/**
 * Created by matt on 2017/4/14.
 */
public class WebViewPresenter extends BeamBasePresenter<WebViewActivity> {

    @Override
    protected void onCreateView(@NonNull WebViewActivity view) {
        super.onCreateView(view);

        initParam();
    }

    private void initParam() {
        setUpWebViewDefaults(getView().webView);
        getView().webView.setWebViewClient(new MyWebViewClient(getView()));
        //设置显示进度条
        getView().webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100)
                    getView().progressBar.setVisibility(View.GONE);
                else
                    getView().progressBar.setProgress(newProgress);
            }
        });
        getView().webView.loadUrl(getView().getIntent().getStringExtra("url"));
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpWebViewDefaults(WebView webView) {
        WebSettings settings = webView.getSettings();

        // 网页内容的宽度是否可大于WebView控件的宽度
        settings.setLoadWithOverviewMode(false);
        // 保存表单数据
        settings.setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        // 启动应用缓存
        settings.setAppCacheEnabled(true);
        // 设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // setDefaultZoom  api19被弃用
        // 设置此属性，可任意比例缩放。
        settings.setUseWideViewPort(true);
        // 缩放比例 1
        webView.setInitialScale(1);
        // 告诉WebView启用JavaScript执行。默认的是false。
        settings.setJavaScriptEnabled(true);
        //  页面加载好以后，再放开图片
        settings.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        settings.setDomStorageEnabled(true);
        // 排版适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // WebView是否支持多个窗口。
        settings.setSupportMultipleWindows(true);

        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        /** 设置字体默认缩放大小(改变网页字体大小,setTextSize  api14被弃用)*/
        settings.setTextZoom(100);

        // Enable remote debugging via chrome://inspect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        // AppRTC requires third party cookies to work
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptThirdPartyCookies(webView, true);


    }

}
