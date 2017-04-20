package com.zmj.qvod.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zmj.qvod.R;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.VideoInfo;

/**
 * Created by matt on 2017/3/17.
 */

public class RecommendViewHolder extends BaseViewHolder<VideoInfo> {


    ImageView ivVideo;
    TextView tvTitle;

    public RecommendViewHolder(ViewGroup group) {
        super(group, R.layout.item_video);
        ivVideo = $(R.id.iv_video);
        tvTitle = $(R.id.tv_title);
    }

    @Override
    public void setData(VideoInfo data) {
        tvTitle.setText(data.title);
        ImageLoader.load(data.pic, ivVideo, 320);
    }

}

