package com.zmj.qvod.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.zmj.qvod.R;
import com.zmj.qvod.presenter.SplashPresenter;
import com.zmj.qvod.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresPresenter(SplashPresenter.class)
public class SplashActivity extends BeamBaseActivity<SplashPresenter> {

    @BindView(R.id.iv_splash_bg)
    ImageView ivSplashBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        AppUtils.steepStatusBar(this);
    }

    public ImageView getImageView() {
        return ivSplashBg;
    }


    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    public void startMainActivity() {
        MainActivity.startActivity(this);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
    }
}
