package com.zmj.qvod.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.utils.JUtils;
import com.weavey.loading.lib.LoadingLayout;
import com.zmj.qvod.R;
import com.zmj.qvod.module.bean.HotMovieBean;
import com.zmj.qvod.presenter.MovieDetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by matt on 2017/4/6.
 */
@RequiresPresenter(MovieDetailsPresenter.class)
public class MovieDetailsActivity extends BeamBaseActivity<MovieDetailsPresenter> {

    @BindView(R.id.iv_movie_pic)
    public ImageView ivMoviePic;
    @BindView(R.id.iv_dim_bg)
    public ImageView ivDimBg;
    @BindView(R.id.tv_details_title)
    public TextView tvDetailsTitle;
    @BindView(R.id.tv_details_direct)
    public TextView tvDetailsDirect;
    @BindView(R.id.tv_details_actor)
    public TextView tvDetailsActor;
    @BindView(R.id.tv_details_type)
    public TextView tvDetailsType;
    @BindView(R.id.tv_details_date)
    public TextView tvDetailsDate;
    @BindView(R.id.tv_details_country)
    public TextView tvDetailsCountry;
    @BindView(R.id.toolbar)
    public Toolbar toolBar;
    @BindView(R.id.nsv_title)
    public NestedScrollView nsvTitle;
    @BindView(R.id.iv_toolbar_bg)
    public ImageView ivToolbarBg;
    @BindView(R.id.tv_movie_call)
    public TextView tvMovieCall;
    @BindView(R.id.tv_movie_intro)
    public TextView tvMovieIntro;
    @BindView(R.id.erv_movie_list)
    public EasyRecyclerView recyclerView;
    @BindView(R.id.ll_movie_loading)
    public LoadingLayout llLoading;

    public static void startAction(Activity context, HotMovieBean.SubjectsBean positionData, ImageView imageView) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra("positionData", positionData);
        ActivityOptionsCompat compat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context, imageView, context.getResources().getString(R.string.transition_movie_img));
        ActivityCompat.startActivity(context, intent, compat.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        ButterKnife.bind(this);
        //
        StatusBarUtil.setTranslucentForImageView(this, 0, toolBar);
        //
        onSetToolbar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //是否显示默认Title
            actionBar.setDisplayShowTitleEnabled(true);
            //是否显示返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //
        initLoading();
    }

    private void initLoading() {
        //网络错误之类的
        if (!JUtils.isNetWorkAvilable()) {
            llLoading.setStatus(LoadingLayout.No_Network);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }
}
