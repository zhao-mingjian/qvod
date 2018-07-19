package com.zmj.qvod.presenter;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.jude.utils.JUtils;
import com.zmj.qvod.R;
import com.zmj.qvod.module.bean.VideoType;
import com.zmj.qvod.module.service.HttpUtils;
import com.zmj.qvod.utils.BeanUtil;
import com.zmj.qvod.utils.RxUtil;
import com.zmj.qvod.utils.StringUtils;
import com.zmj.qvod.view.activity.VideoInfoActivity;
import com.zmj.qvod.view.fragment.VideoListFragment;

import java.util.Map;

import rx.Subscription;

/**
 * Created by matt on 2017/3/20.
 */

public class VideoListPresenter extends BeamListFragmentPresenter<VideoListFragment, VideoType> implements View.OnClickListener {

    private Subscription subscription;
    private String catalogId;//id
    private int page = 1;//分页
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreateView(@NonNull VideoListFragment view) {
        super.onCreateView(view);
        //
        initView();
        //
        initData();
        //
        onRefresh();
        //
        initEvent();
    }

    private void initEvent() {
        getAdapter().setOnItemClickListener(position -> {
            /*onClick*/
            VideoInfoActivity.actionStart(getView().getContext(), BeanUtil.VideoType2VideoInfo(getAdapter().getItem(position)));
        });
        getView().getListView().getErrorView().findViewById(R.id.view_net_btn).setOnClickListener(this);
        getView().getListView().getEmptyView().findViewById(R.id.view_empty_btn).setOnClickListener(this);
        //
    }

    private void initData() {
        String url = getView().getArguments().getString("tab");
        // 解析URL
        Map<String, String> map = StringUtils.URLRequest(url);
        catalogId = map.get("catalogId");
    }

    private void initView() {
        gridLayoutManager = new GridLayoutManager(getView().getContext(), 3);
        gridLayoutManager.setSpanSizeLookup(getAdapter().obtainGridSpanSizeLookUp(3));//“没有数据了”View居中
        getView().getListView().setLayoutManager(gridLayoutManager);
        SpaceDecoration itemDecoration = new SpaceDecoration(JUtils.dip2px(8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        getView().getListView().addItemDecoration(itemDecoration);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        page = 1;
        getVideoList();
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        page++;
        getVideoList();
    }

    private void getVideoList() {
        subscription = HttpUtils.getInstance().getVideoServer().getVideoList(catalogId, page + "")
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())
                .subscribe(videoRes -> {
                    if (page == 1) getAdapter().clear();
                    getAdapter().addAll(videoRes.list);
                }, throwable -> {
                    if (page > 1) page--;
                    new Handler().postDelayed(() -> {
                        getView().stopRefresh();
                        getView().showError(throwable);
                    }, 500);
                }, () -> {
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.view_net_btn) {
            getView().getListView().setRefreshing(true);
            onRefresh();
        } else if (view.getId() == R.id.view_empty_btn) {
            getView().getListView().setRefreshing(true);
            onRefresh();
        }
    }
}
