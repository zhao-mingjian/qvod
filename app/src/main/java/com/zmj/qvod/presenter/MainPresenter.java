package com.zmj.qvod.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.jude.beam.bijection.Presenter;
import com.zmj.qvod.adapter.MyFragmentPagerAdapter;
import com.zmj.qvod.view.activity.MainActivity;
import com.zmj.qvod.view.fragment.IndexFragment;
import com.zmj.qvod.view.fragment.MineFragment;
import com.zmj.qvod.view.fragment.RankingListFragment;

import java.util.ArrayList;

/**
 * Created by matt on 2017/2/17.
 */
public class MainPresenter extends Presenter<MainActivity> {


    @Override
    protected void onCreateView(@NonNull MainActivity view) {
        super.onCreateView(view);

        initContentFragment();

    }

    private void initContentFragment() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(IndexFragment.newInstance());
        fragmentArrayList.add(RankingListFragment.newInstance());
        fragmentArrayList.add(MineFragment.newInstance());
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getView().getSupportFragmentManager(), fragmentArrayList);
        getView().getViewPager().setAdapter(pagerAdapter);
    }


}
