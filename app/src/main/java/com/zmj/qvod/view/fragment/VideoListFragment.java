package com.zmj.qvod.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zmj.qvod.adapter.viewholder.VideoListViewHolder;
import com.zmj.qvod.config.Constant;
import com.zmj.qvod.module.bean.VideoType;
import com.zmj.qvod.presenter.VideoListPresenter;

/**
 * Created by matt on 2017/3/20.
 */

@RequiresPresenter(VideoListPresenter.class)
public class VideoListFragment extends BeamListFragment<VideoListPresenter, VideoType> {

    @Override
    public ListConfig getConfig() {
        return Constant.getLoadMoreConfig();
    }

    public static Fragment newInstance(String tab) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab", tab);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public BaseViewHolder<VideoType> getViewHolder(ViewGroup parent, int viewType) {
        return new VideoListViewHolder(parent);
    }



}
