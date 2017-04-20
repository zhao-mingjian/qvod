package com.zmj.qvod.adapter.viewholder;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zmj.qvod.R;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.HotMovieBean;
import com.zmj.qvod.view.activity.MovieDetailsActivity;

/**
 * Created by matt on 2017/3/30.
 */
public class MovieTopViewHolder extends BaseViewHolder<HotMovieBean.SubjectsBean> {

    private ImageView ivVideo;
    private TextView tvTitle;

    public MovieTopViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_video_info);
        ivVideo = $(R.id.iv_video_info);
        tvTitle = $(R.id.tv_title_info);
    }

    @Override
    public void setData(HotMovieBean.SubjectsBean data) {
        super.setData(data);
        tvTitle.setText(data.getTitle() + "\n评分：" + data.getRating().getAverage() + "分");
        ImageLoader.load(data.getImages().getLarge(), ivVideo, 200);

        itemView.setOnClickListener(view -> MovieDetailsActivity.startAction((Activity) getContext(), data, ivVideo));
    }

}
