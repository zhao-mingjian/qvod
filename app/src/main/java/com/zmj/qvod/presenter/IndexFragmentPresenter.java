package com.zmj.qvod.presenter;

import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.utils.JUtils;
import com.weavey.loading.lib.LoadingLayout;
import com.zmj.qvod.R;
import com.zmj.qvod.adapter.MyFragmentPagerAdapter;
import com.zmj.qvod.app.VideoApplication;
import com.zmj.qvod.module.bean.VideoRes;
import com.zmj.qvod.module.bean.VideoType;
import com.zmj.qvod.module.service.HttpUtils;
import com.zmj.qvod.utils.RxUtil;
import com.zmj.qvod.view.fragment.IndexFragment;
import com.zmj.qvod.view.fragment.RecommendFragment;
import com.zmj.qvod.view.fragment.VideoListFragment;

import java.util.ArrayList;

import rx.Subscription;

/**
 * Created by matt on 2017/3/14.
 */

public class IndexFragmentPresenter extends BeamBasePresenter<IndexFragment> {

    private ArrayList<String> mTitleList = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    //
    private Subscription subscription;
    //
    private VideoRes videoRes;

    @Override
    protected void onCreateView(@NonNull IndexFragment view) {
        super.onCreateView(view);

        initLoading();
        //
        initEvent();
    }

    private void initEvent() {
        //点击重试
        getView().getLlLoading().setOnReloadListener(v -> {
            if (JUtils.isNetWorkAvilable())
                //onClick
                initData();
        });
    }

    private void initLoading() {
        //加载中
        getView().getLlLoading().setStatus(LoadingLayout.Loading);
        //网络错误之类的
        if (!JUtils.isNetWorkAvilable()) {
            getView().getLlLoading().setStatus(LoadingLayout.No_Network);
            return;
        }
        //
        initData();
    }

    private void initFragmentList() {
        if (videoRes == null) return;
        mTitleList.add("首页精选");
        mFragments.add(RecommendFragment.newInstance());
        for (VideoType videoType : videoRes.list) {
            if (!TextUtils.isEmpty(videoType.moreURL) && !TextUtils.isEmpty(videoType.title)) {
                mTitleList.add(videoType.title);
                mFragments.add(VideoListFragment.newInstance(videoType.moreURL));
            }
        }
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getView().getFragmentManager(), mFragments, mTitleList);
        getView().getVpIndex().setAdapter(pagerAdapter);
        getView().getTlIndex().setupWithViewPager(getView().getVpIndex());
    }

    private void initData() {
        //数据为空再请求
        if (videoRes != null) return;
        subscription = HttpUtils.getInstance().getVideoServer().getIndexPage()
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribe(videoRes -> {
                    // onNext()
                    IndexFragmentPresenter.this.videoRes = videoRes;
                    VideoApplication.getInstance().setVideoRes(videoRes);
                    //
                    initFragmentList();
                    //
                    if (videoRes != null) {
                        getView().getLlLoading().setStatus(LoadingLayout.Success);
                        return;
                    }
                    getView().getLlLoading().setStatus(LoadingLayout.Empty);
                }, throwable -> {
                    // onError()
                    getView().getLlLoading().setStatus(LoadingLayout.Error);
                }, () -> {
                    // onCompleted()
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

}
