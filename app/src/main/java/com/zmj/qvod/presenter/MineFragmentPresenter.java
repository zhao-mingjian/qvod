package com.zmj.qvod.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.jude.beam.expansion.BeamBasePresenter;
import com.zmj.qvod.R;
import com.zmj.qvod.adapter.MyFragmentPagerAdapter;
import com.zmj.qvod.view.fragment.MineFragment;
import com.zmj.qvod.view.fragment.PictureFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 2017/4/18.
 */

public class MineFragmentPresenter extends BeamBasePresenter<MineFragment> {

    @Override
    protected void onCreateView(@NonNull MineFragment view) {
        super.onCreateView(view);

        initView();
    }

    private void initView() {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        //
        String[] tabs = getView().getResources().getStringArray(R.array.tab);
        //titles = Arrays.asList(tabs);
        for (String tab : tabs) {
            fragments.add(PictureFragment.newInstance(tab));
            titles.add(tab);
        }
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getView().getFragmentManager(), fragments, titles);
        getView().vpMine.setAdapter(pagerAdapter);
        getView().tlMine.setupWithViewPager(getView().vpMine);
    }
}
