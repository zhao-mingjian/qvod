package com.zmj.qvod.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zmj.qvod.R;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.VideoInfo;

/**
 * Created by matt on 2017/3/30.
 */

public class VideoMoreViewHolder extends BaseViewHolder<VideoInfo> {

    private ImageView ivVideo;
    private TextView tvTitle;

    public VideoMoreViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_video_info);
        ivVideo = $(R.id.iv_video_info);
        tvTitle = $(R.id.tv_title_info);
    }

    @Override
    public void setData(VideoInfo data) {
        tvTitle.setText(data.title);
        ImageLoader.load(data.pic, ivVideo, 200);
    }

}
