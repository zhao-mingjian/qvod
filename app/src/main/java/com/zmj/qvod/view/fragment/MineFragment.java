package com.zmj.qvod.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.zmj.qvod.R;
import com.zmj.qvod.presenter.MineFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by matt on 2017/4/18.
 */
@RequiresPresenter(MineFragmentPresenter.class)
public class MineFragment extends BeamFragment<MineFragmentPresenter> {

    @BindView(R.id.tl_mine)
    public TabLayout tlMine;
    @BindView(R.id.vp_mine)
    public ViewPager vpMine;

    private Unbinder unbinder;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
