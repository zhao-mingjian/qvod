package com.zmj.qvod.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.zmj.qvod.R;
import com.zmj.qvod.presenter.RecommendPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by matt on 2017/3/14.
 */

@RequiresPresenter(RecommendPresenter.class)
public class RecommendFragment extends BeamFragment<RecommendPresenter> {

    @BindView(R.id.rv_easy)
    EasyRecyclerView rvEasy;

    private Unbinder bind;

    //
    public static Fragment newInstance() {
        return new RecommendFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    public EasyRecyclerView getRvEasy() {
        return rvEasy;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
