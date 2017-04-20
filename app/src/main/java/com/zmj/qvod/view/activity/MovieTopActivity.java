package com.zmj.qvod.view.activity;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zmj.qvod.adapter.viewholder.MovieTopViewHolder;
import com.zmj.qvod.config.Constant;
import com.zmj.qvod.module.bean.HotMovieBean;
import com.zmj.qvod.presenter.MovieListPresenter;

/**
 * Created by matt on 2017/4/6.
 */
@RequiresPresenter(MovieListPresenter.class)
public class MovieTopActivity extends BeamListActivity<MovieListPresenter, HotMovieBean.SubjectsBean> {

    public static void startAction(Context context) {
        Intent intent = new Intent(context, MovieTopActivity.class);
        context.startActivity(intent);
    }

    @Override
    public ListConfig getConfig() {
        return Constant.getLoadMoreConfig();
    }

    @Override
    public BaseViewHolder<HotMovieBean.SubjectsBean> getViewHolder(ViewGroup parent, int viewType) {
        return new MovieTopViewHolder(parent);
    }

}
