package com.zmj.qvod.presenter;

import android.support.annotation.NonNull;

import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.utils.JUtils;
import com.zmj.qvod.view.activity.AboutActivity;

/**
 * Created by Administrator on 2017/4/20.
 */
public class AboutPresenter extends BeamBasePresenter<AboutActivity> {

    @Override
    protected void onCreateView(@NonNull AboutActivity view) {
        super.onCreateView(view);

        getView().setTitle("关于快播");

        getView().tvVersion.setText("当前版本：V" + JUtils.getAppVersionName());
    }
}
