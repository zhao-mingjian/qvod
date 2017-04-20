package com.zmj.qvod.adapter;


import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zmj.qvod.adapter.viewholder.RecommendViewHolder;
import com.zmj.qvod.module.bean.VideoInfo;

/**
 * Created by matt on 2017/3/14.
 */

public class RecommendAdapter extends RecyclerArrayAdapter<VideoInfo> {


    public RecommendAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendViewHolder(parent);
    }


}
