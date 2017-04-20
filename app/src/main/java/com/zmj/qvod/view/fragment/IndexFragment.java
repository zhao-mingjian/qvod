package com.zmj.qvod.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.weavey.loading.lib.LoadingLayout;
import com.zmj.qvod.R;
import com.zmj.qvod.presenter.IndexFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@RequiresPresenter(IndexFragmentPresenter.class)
public class IndexFragment extends BeamFragment<IndexFragmentPresenter> {

    @BindView(R.id.tl_index)
    TabLayout tlIndex;
    @BindView(R.id.vp_index)
    ViewPager vpIndex;
    @BindView(R.id.ll_loading)
    LoadingLayout llLoading;
    private Unbinder bind;

    public IndexFragment() {
    }

    public static IndexFragment newInstance() {
        IndexFragment indexFragment = new IndexFragment();
        return indexFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    public TabLayout getTlIndex() {
        return tlIndex;
    }

    public ViewPager getVpIndex() {
        return vpIndex;
    }

    public LoadingLayout getLlLoading() {
        return llLoading;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
