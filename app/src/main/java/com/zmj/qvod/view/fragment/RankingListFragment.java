package com.zmj.qvod.view.fragment;

import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zmj.qvod.adapter.viewholder.HotMovieViewHolder;
import com.zmj.qvod.config.Constant;
import com.zmj.qvod.module.bean.HotMovieBean;
import com.zmj.qvod.presenter.RankingListPresenter;

/**
 * Created by matt on 2017/3/30.
 */
@RequiresPresenter(RankingListPresenter.class)
public class RankingListFragment extends BeamListFragment<RankingListPresenter, HotMovieBean.SubjectsBean> {

    @Override
    public ListConfig getConfig() {
        return Constant.getNoMoreConfig();
    }

    public static RankingListFragment newInstance() {
        return new RankingListFragment();
    }

    @Override
    public BaseViewHolder<HotMovieBean.SubjectsBean> getViewHolder(ViewGroup parent, int viewType) {
        return new HotMovieViewHolder(parent);
    }

}
