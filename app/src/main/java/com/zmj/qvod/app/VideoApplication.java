package com.zmj.qvod.app;

import android.app.Application;

import com.jude.utils.JUtils;
import com.weavey.loading.lib.LoadingLayout;
import com.zmj.qvod.R;
import com.zmj.qvod.module.bean.VideoRes;
import com.zmj.qvod.module.service.HttpUtils;

/**
 * Created by matt on 2016/11/22.
 */
public class VideoApplication extends Application {

    private static VideoApplication videoApplication;

    public static VideoApplication getInstance() {
        if (videoApplication == null) {
            synchronized (VideoApplication.class) {
                if (videoApplication == null) {
                    videoApplication = new VideoApplication();
                }
            }
        }
        return videoApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        videoApplication = this;
        HttpUtils.getInstance().setContext(this);
        JUtils.initialize(this);
        JUtils.setDebug(true, "video");
        initLoading();
    }

    private void initLoading() {
        LoadingLayout.getConfig()
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("抱歉，暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                .setErrorImage(R.drawable.ic_empty_icon)
                .setEmptyImage(R.drawable.ic_empty_icon)
                .setNoNetworkImage(R.drawable.ic_no_network)
                .setAllTipTextColor(R.color.colorTabText)
                .setAllTipTextSize(14)
                .setReloadButtonText("点我重试哦")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(R.color.colorTabText)
                .setReloadButtonWidthAndHeight(150, 40)
                .setAllPageBackgroundColor(R.color.colorBackground)
                .setLoadingPageLayout(R.layout.default_loading_page);
    }

    private VideoRes videoRes;

    public VideoRes getVideoRes() {
        return videoRes;
    }

    public void setVideoRes(VideoRes videoRes) {
        this.videoRes = videoRes;
    }
}
