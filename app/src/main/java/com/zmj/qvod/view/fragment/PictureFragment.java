package com.zmj.qvod.view.fragment;

import android.os.Bundle;
import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zmj.qvod.adapter.viewholder.PictureViewHolder;
import com.zmj.qvod.config.Constant;
import com.zmj.qvod.module.bean.PicBean;
import com.zmj.qvod.presenter.PictureFragmentPresenter;

/**
 * Created by matt on 2017/4/17.
 */
@RequiresPresenter(PictureFragmentPresenter.class)
public class PictureFragment extends BeamListFragment<PictureFragmentPresenter, PicBean.ItemsBean> {


    public static PictureFragment newInstance(String tab) {
        PictureFragment pictureFragment = new PictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab", tab);
        pictureFragment.setArguments(bundle);
        return pictureFragment;
    }

    @Override
    public ListConfig getConfig() {
        return Constant.getLoadMoreConfig();
    }

    @Override
    public BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new PictureViewHolder(parent);
    }
}
