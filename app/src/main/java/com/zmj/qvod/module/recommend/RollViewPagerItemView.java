package com.zmj.qvod.module.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.swipe.SwipeRefreshLayout;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.IconHintView;
import com.jude.utils.JUtils;
import com.zmj.qvod.R;
import com.zmj.qvod.adapter.ImageLoopAdapter;
import com.zmj.qvod.app.VideoApplication;
import com.zmj.qvod.module.bean.VideoInfo;
import com.zmj.qvod.module.bean.VideoRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 2017/3/15.
 */

public class RollViewPagerItemView implements RecyclerArrayAdapter.ItemView {

    private RollPagerView rollPagerView;
    private Context mContext;
    private ImageLoopAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    //
    private List<VideoInfo> banners;

    public RollViewPagerItemView(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    public View onCreateView(ViewGroup parent) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.include_viewpager, parent, false);
        rollPagerView = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        //解决viewPager和swipeRefreshLayout的冲突
        //...
        //
        initData();
        return view;
    }

    private void initData() {
        banners = new ArrayList<>();
        //过滤广告
        removeEmpty();
        //
        adapter = new ImageLoopAdapter();
        adapter.setBanners(banners);
        rollPagerView.setHintView(new IconHintView(mContext, R.drawable.ic_page_indicator_focused, R.drawable.ic_page_indicator, JUtils.dip2px(10)));
        rollPagerView.setAdapter(adapter);
    }

    private void removeEmpty() {
        VideoRes videoRes = VideoApplication.getInstance().getVideoRes();
        for (VideoInfo videoInfo : videoRes.list.get(0).childList) {
            if (videoInfo.loadType.equals("video")) {
                banners.add(videoInfo);
            }
        }
    }

    @Override
    public void onBindView(View headerView) {

    }
}
