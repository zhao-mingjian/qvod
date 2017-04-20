package com.zmj.qvod.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.utils.JUtils;
import com.zmj.qvod.R;
import com.zmj.qvod.module.bean.HotMovieBean;
import com.zmj.qvod.module.recommend.HeadItemView;
import com.zmj.qvod.module.service.HttpUtils;
import com.zmj.qvod.utils.RxUtil;
import com.zmj.qvod.view.activity.MovieTopActivity;
import com.zmj.qvod.view.fragment.RankingListFragment;

import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;

/**
 * Created by matt on 2017/3/30.
 */

public class RankingListPresenter extends BeamListFragmentPresenter<RankingListFragment, HotMovieBean.SubjectsBean> implements View.OnClickListener {

    private Subscription rxSubscription;


    @Override
    protected void onCreateView(@NonNull RankingListFragment view) {
        super.onCreateView(view);

        initView();

        onRefresh();

        initEvent();

    }

    private void initView() {
        getAdapter().addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                View headerView = LayoutInflater.from(getView().getContext()).inflate(R.layout.include_ranking_head, null);
                RelativeLayout relativeLayout = ButterKnife.findById(headerView, R.id.rv_douban_click);
                relativeLayout.setOnClickListener(view -> {
                    MovieTopActivity.startAction(getView().getContext());
                });
                return headerView;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        getAdapter().addHeader(new HeadItemView("热映榜"));
        //
        getView().getListView().setLayoutManager(new LinearLayoutManager(getView().getContext()));
    }

    private void initEvent() {
//        getAdapter().setOnItemClickListener(position -> {
//            JUtils.Toast("点击了Item：" + position);
//        });
        //
        getView().getListView().getErrorView().findViewById(R.id.view_net_btn).setOnClickListener(this);
        getView().getListView().getEmptyView().findViewById(R.id.view_empty_btn).setOnClickListener(this);
    }

    private void initData() {
        rxSubscription = HttpUtils.getInstance().getMovieServer().getHotMovie()
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<HotMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        getView().stopRefresh();
                        getView().showError(throwable);
                    }

                    @Override
                    public void onNext(HotMovieBean hotMovieBean) {
                        getAdapter().clear();
                        getAdapter().addAll(hotMovieBean.getSubjects());
                    }
                });
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxSubscription.unsubscribe();
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
