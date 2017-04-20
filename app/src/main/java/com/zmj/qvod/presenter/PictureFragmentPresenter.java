package com.zmj.qvod.presenter;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zmj.qvod.R;
import com.zmj.qvod.module.bean.PicBean;
import com.zmj.qvod.module.service.HttpUtils;
import com.zmj.qvod.utils.RxUtil;
import com.zmj.qvod.view.activity.ImageBrowseActivity;
import com.zmj.qvod.view.fragment.PictureFragment;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;

/**
 * Created by matt on 2017/4/17.
 */
public class PictureFragmentPresenter extends BeamListFragmentPresenter<PictureFragment, PicBean.ItemsBean> implements RecyclerArrayAdapter.OnItemClickListener, View.OnClickListener {

    private String tab;
    private int page;
    private Subscription rxSubscription;
    private PicBean picBean;

    @Override
    protected void onCreateView(@NonNull PictureFragment view) {
        super.onCreateView(view);

        initPrams();

        initAdapter();

        onRefresh();

        initEvent();
    }

    private void initEvent() {
        getView().getListView().getErrorView().findViewById(R.id.view_net_btn).setOnClickListener(this);
        getView().getListView().getEmptyView().findViewById(R.id.view_empty_btn).setOnClickListener(this);
    }

    private void initPrams() {
        tab = getView().getArguments().getString("tab");
    }

    private void initAdapter() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        getView().getListView().setLayoutManager(staggeredGridLayoutManager);
        getAdapter().setOnItemClickListener(PictureFragmentPresenter.this);
    }

    private void initData(String search, int page) {
        rxSubscription = HttpUtils.getInstance().getPicServer().getPicture("ajax", "result", search, page)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<PicBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().stopRefresh();
                        getView().showError(e);
                    }

                    @Override
                    public void onNext(PicBean picBean) {
                        if (page == 0) {
                            getAdapter().clear();
                            PictureFragmentPresenter.this.picBean = picBean;
                        } else {
                            PictureFragmentPresenter.this.picBean.getItems().addAll(picBean.getItems());
                        }
                        getAdapter().addAll(picBean.getItems());
                    }
                });
    }


    @Override
    public void onRefresh() {
        super.onRefresh();
        page = 0;
        initData(tab, page * 48);
    }

    @Override
    public void onLoadMore() {
        super.onLoadMore();
        page += 1;
        initData(tab, page * 48);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (rxSubscription.isUnsubscribed()) rxSubscription.unsubscribe();
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    public void onItemClick(int position) {
        ImageBrowseActivity.startAction(getView().getContext(), (ArrayList<PicBean.ItemsBean>) picBean.getItems(), position);
        //设置跳转无效果_01
        getView().getActivity().overridePendingTransition(0, 0);
        //设置跳转无效果_02
        //intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
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
