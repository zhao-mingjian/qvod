package com.zmj.qvod.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.zmj.qvod.module.bean.HotMovieBean;
import com.zmj.qvod.module.service.HttpUtils;
import com.zmj.qvod.utils.RxUtil;
import com.zmj.qvod.view.activity.MovieTopActivity;

import rx.Observer;
import rx.Subscription;

/**
 * Created by matt on 2017/4/6.
 */

public class MovieListPresenter extends BeamListActivityPresenter<MovieTopActivity, HotMovieBean.SubjectsBean> {

    //分页
    private int mStart = 0;
    private int mCount = 21;
    private Subscription rxSubscription;

    @Override
    protected void onCreateView(@NonNull MovieTopActivity view) {
        super.onCreateView(view);

        initView();
        //
        onRefresh();
        //
        initEvent();
    }

    private void initEvent() {
//        getAdapter().setOnItemClickListener(position ->
//
//        );
    }

    private void initView() {
        //
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getView(), 3);
        gridLayoutManager.setSpanSizeLookup(getAdapter().obtainGridSpanSizeLookUp(3));//“没有数据了”View居中
        getView().getListView().setLayoutManager(gridLayoutManager);
    }

    private void initData() {
        rxSubscription = HttpUtils.getInstance().getMovieServer().getMovieTop(mStart, mCount)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<HotMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mStart >= mCount) mStart -= mCount;
                        getView().stopRefresh();
                        getView().showError(e);
                    }

                    @Override
                    public void onNext(HotMovieBean movieTop) {
                        if (mStart == 0) getAdapter().clear();
                        getAdapter().addAll(movieTop.getSubjects());
                    }
                });
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mStart = 0;
        initData();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        mStart += mCount;
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxSubscription.unsubscribe();
    }
}
