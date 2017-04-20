package com.zmj.qvod.presenter;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.jude.beam.expansion.BeamBasePresenter;
import com.zmj.qvod.adapter.RecommendAdapter;
import com.zmj.qvod.app.VideoApplication;
import com.zmj.qvod.module.bean.VideoInfo;
import com.zmj.qvod.module.bean.VideoRes;
import com.zmj.qvod.module.recommend.HeadItemView;
import com.zmj.qvod.module.recommend.RollViewPagerItemView;
import com.zmj.qvod.view.activity.VideoInfoActivity;
import com.zmj.qvod.view.fragment.RecommendFragment;

import java.util.List;

/**
 * Created by matt on 2017/3/14.
 */

public class RecommendPresenter extends BeamBasePresenter<RecommendFragment> implements SwipeRefreshLayout.OnRefreshListener {


    private RecommendAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreateView(@NonNull RecommendFragment view) {
        super.onCreateView(view);

        initView();
        initEvent();
    }

    private void initEvent() {
        adapter.setOnItemClickListener(position -> {
            VideoInfoActivity.actionStart(getView().getContext(), adapter.getItem(position));
        });
    }

    private void initView() {
        //
        adapter = new RecommendAdapter(getView().getContext());
        //
        if (adapter.getHeaderCount() == 0) {
            adapter.addHeader(new RollViewPagerItemView(getView().getRvEasy().getSwipeToRefresh()));
            adapter.addHeader(new HeadItemView("精彩推荐"));
        }
        layoutManager = new LinearLayoutManager(getView().getContext());
        getView().getRvEasy().setLayoutManager(layoutManager);
        getView().getRvEasy().setItemAnimator(new DefaultItemAnimator());
        getView().getRvEasy().setAdapterWithProgress(adapter);
        getView().getRvEasy().setRefreshListener(this);
        //
        VideoRes videoRes = VideoApplication.getInstance().getVideoRes();
        if (videoRes == null) return;
        for (int i = 1; i < videoRes.list.size(); i++) {
            if (videoRes.list.get(i).title.equals("精彩推荐")) {
                List<VideoInfo> videoInfos = videoRes.list.get(i).childList;
                adapter.addAll(videoInfos);
                break;
            }
        }
        //
//        if (adapter != null && adapter.getCount() == 0) {
//            if (JUtils.isNetWorkAvilable()) {
//                getView().getRvEasy().showEmpty();
//            } else {
//                getView().getRvEasy().showError();
//            }
//        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> {
            getView().getRvEasy().getSwipeToRefresh().setRefreshing(false);
        }, 500);
    }
}
