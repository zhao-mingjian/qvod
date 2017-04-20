package com.zmj.qvod.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zmj.qvod.R;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.MovieDetailBean;
import com.zmj.qvod.view.activity.WebViewActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by matt on 2017/4/13.
 */

public class MovieCastsViewHolder extends BaseViewHolder<MovieDetailBean.CastsBean> {


    private CircleImageView civCastsPic;
    private TextView tvCastsName;
    private TextView tvCastsType;

    public MovieCastsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_movie_casts);
        civCastsPic = $(R.id.civ_casts_pic);
        tvCastsName = $(R.id.tv_casts_name);
        tvCastsType = $(R.id.tv_casts_type);
    }

    @Override
    public void setData(MovieDetailBean.CastsBean data) {
        super.setData(data);
        ImageLoader.load(getContext(), data.getAvatars().getLarge(), civCastsPic);
        tvCastsName.setText(data.getName());
        //
        if ("导演".equals(data.getType()))
            tvCastsType.setText("导演");
        else
            tvCastsType.setText("演员");
        //
        itemView.setOnClickListener(view -> {
            //onClick
            WebViewActivity.startAction(getContext(), data.getAlt());
        });
    }
}
