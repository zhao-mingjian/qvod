package com.zmj.qvod.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.zmj.qvod.R;
import com.zmj.qvod.presenter.WebViewPresenter;
import com.zmj.qvod.widght.TitleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matt on 2017/4/14.
 */
@RequiresPresenter(WebViewPresenter.class)
public class WebViewActivity extends BeamBaseActivity<WebViewPresenter> {

    @BindView(R.id.wv_web)
    public WebView webView;
    @BindView(R.id.tl_toolbar)
    public TitleLayout tlToolbar;
    @BindView(R.id.pb_web_view)
    public ProgressBar progressBar;

    public static void startAction(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            // 返回前一个页面
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.clearCache(true);
            webView.destroy();
        }
    }
}
