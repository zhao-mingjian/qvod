package com.zmj.qvod.presenter;

import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.jude.utils.JUtils;
import com.zmj.qvod.R;
import com.zmj.qvod.adapter.VideoMoreAdapter;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.VideoRes;
import com.zmj.qvod.module.service.HttpUtils;
import com.zmj.qvod.utils.RxUtil;
import com.zmj.qvod.utils.StringUtils;
import com.zmj.qvod.view.activity.VideoInfoActivity;
import com.zmj.qvod.widght.TextViewExpandableAnimation;

import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import rx.Subscription;

/**
 * Created by matt on 2017/3/24.
 */

public class VideoInfoPresenter extends BeamBasePresenter<VideoInfoActivity> {

    private JCVideoPlayerStandard jcVideoPlay;

    private VideoRes videoRes;
    private Subscription rxSubscription;
    private VideoMoreAdapter adapter;
    private View headView;

    TextViewExpandableAnimation tvExpand;

    @Override
    protected void onCreateView(@NonNull VideoInfoActivity view) {
        super.onCreateView(view);
        getView().getSupportActionBar().setTitle(getView().getVideoInfo().title);

        initVideoPlay();

        setListView();

        initData();

    }

    private void initVideoPlay() {
        jcVideoPlay = getView().getJcVideoPlay();
        //
        jcVideoPlay.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        jcVideoPlay.backButton.setVisibility(View.GONE);
        jcVideoPlay.titleTextView.setVisibility(View.GONE);
        jcVideoPlay.tinyBackImageView.setVisibility(View.GONE);
        //设置视频占位图
        ImageLoader.load(getView().getVideoInfo().pic, jcVideoPlay.thumbImageView, 320);
    }

    private void playVideo() {
        if (videoRes == null) return;
        //
        jcVideoPlay.setUp(videoRes.getVideoUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoRes.title);
        //自动播放
        jcVideoPlay.startPlayLogic();
        //点击全屏_禁用重力感应
        JCVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

    private void setIntro() {
        String dir = "导演：" + StringUtils.removeOtherCode(videoRes.director);
        String act = "主演：" + StringUtils.removeOtherCode(videoRes.actors);
        String des = dir + "\n" + act + "\n" + "简介：" + StringUtils.removeOtherCode(videoRes.description);
        tvExpand.setText(des);
    }

    private void setListView() {
        adapter = new VideoMoreAdapter(getView());
        getView().getListView().setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(getView(), 3);
        manager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(3));
        getView().getListView().setLayoutManager(manager);
        SpaceDecoration decoration = new SpaceDecoration(JUtils.dip2px(8));
        decoration.setPaddingEdgeSide(true);
        decoration.setPaddingStart(true);
        decoration.setPaddingHeaderFooter(true);
        getView().getListView().addItemDecoration(decoration);
        //
        headView = LayoutInflater.from(getView()).inflate(R.layout.include_intro, null);
        tvExpand = ButterKnife.findById(headView, R.id.tv_expand);
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                return headView;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        //
        adapter.setOnItemClickListener(position -> {
            VideoInfoActivity.actionStart(getView(), adapter.getItem(position));
            getView().finish();
        });
    }

    private void initData() {
        rxSubscription = HttpUtils.getInstance().getVideoServer().getVideoInfo(getView().getVideoInfo().dataId)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribe(videoRes -> {
                            VideoInfoPresenter.this.videoRes = videoRes;
                            //标题
                            getView().getSupportActionBar().setTitle(videoRes.title);
                            //
                            playVideo();
                            //
                            setIntro();
                            //
                            if (videoRes.list.size() > 1)
                                adapter.addAll(videoRes.list.get(1).childList);
                            else
                                adapter.addAll(videoRes.list.get(0).childList);
                        },
                        throwable -> {
                            JUtils.Toast("加载失败");
                        },
                        () -> {
                            //加载完成...
                        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxSubscription.unsubscribe();
    }
}
