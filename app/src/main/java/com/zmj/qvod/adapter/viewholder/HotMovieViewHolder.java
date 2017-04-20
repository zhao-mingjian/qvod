package com.zmj.qvod.adapter.viewholder;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zmj.qvod.R;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.HotMovieBean;
import com.zmj.qvod.utils.StringFormatUtil;
import com.zmj.qvod.view.activity.MovieDetailsActivity;

/**
 * Created by matt on 2017/3/30.
 */
public class HotMovieViewHolder extends BaseViewHolder<HotMovieBean.SubjectsBean> {

    private ImageView ivRanking;
    private TextView tvRankingTitle;
    private TextView tvDirectContent;
    private TextView tvActorContent;
    private TextView tvTypeContent;
    private TextView tvScoreContent;

    public HotMovieViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_ranking);
        ivRanking = $(R.id.iv_ranking);
        tvRankingTitle = $(R.id.tv_ranking_title);
        tvDirectContent = $(R.id.tv_direct_content);
        tvActorContent = $(R.id.tv_actor_content);
        tvTypeContent = $(R.id.tv_type_content);
        tvScoreContent = $(R.id.tv_score_content);
    }

    @Override
    public void setData(HotMovieBean.SubjectsBean data) {
        super.setData(data);
        ImageLoader.load(data.getImages().getLarge(), ivRanking, 200);
        tvRankingTitle.setText(data.getTitle());
        tvDirectContent.setText(StringFormatUtil.formatName(data.getDirectors()));
        tvActorContent.setText(StringFormatUtil.formatName(data.getCasts(), true));
        tvTypeContent.setText(StringFormatUtil.formatGenres(data.getGenres()));
        tvScoreContent.setText(String.valueOf(data.getRating().getAverage()));
        //动画
        itemView.setScaleX(0.8f);
        itemView.setScaleY(0.8f);
        itemView.animate().scaleX(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
        itemView.animate().scaleY(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
        //
        itemView.setOnClickListener(view -> MovieDetailsActivity.startAction((Activity) getContext(), data, ivRanking));
    }

}
