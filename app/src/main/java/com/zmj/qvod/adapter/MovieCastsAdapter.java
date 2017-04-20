package com.zmj.qvod.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zmj.qvod.adapter.viewholder.MovieCastsViewHolder;
import com.zmj.qvod.module.bean.MovieDetailBean;

/**
 * Created by matt on 2017/4/13.
 */

public class MovieCastsAdapter extends RecyclerArrayAdapter<MovieDetailBean.CastsBean> {

    public MovieCastsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieCastsViewHolder(parent);
    }
}
