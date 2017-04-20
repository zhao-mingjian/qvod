package com.zmj.qvod.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zmj.qvod.R;
import com.zmj.qvod.module.bean.VideoInfo;
import com.zmj.qvod.presenter.VideoInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by matt on 2017/3/24.
 */
@RequiresPresenter(VideoInfoPresenter.class)
public class VideoInfoActivity extends BeamBaseActivity<VideoInfoPresenter> {

    @BindView(R.id.jc_video_play)
    JCVideoPlayerStandard jcVideoPlay;
    @BindView(R.id.ev_more)
    EasyRecyclerView evMore;


    private VideoInfo videoInfo;

    public static void actionStart(Context context, VideoInfo videoInfo) {
        Intent intent = new Intent(context, VideoInfoActivity.class);
        intent.putExtra("videoInfo", videoInfo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_video_info);
        ButterKnife.bind(this);

        initParam();
    }

    private void initParam() {
        videoInfo = (VideoInfo) getIntent().getSerializableExtra("videoInfo");
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) return;
        super.onBackPressed();
    }

    public JCVideoPlayerStandard getJcVideoPlay() {
        return jcVideoPlay;
    }

    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    public EasyRecyclerView getListView() {
        return evMore;
    }
}
