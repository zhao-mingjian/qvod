package com.zmj.qvod.presenter;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.utils.JUtils;
import com.weavey.loading.lib.LoadingLayout;
import com.zmj.qvod.R;
import com.zmj.qvod.adapter.MovieCastsAdapter;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.HotMovieBean;
import com.zmj.qvod.module.bean.MovieDetailBean;
import com.zmj.qvod.module.service.HttpUtils;
import com.zmj.qvod.utils.AppUtils;
import com.zmj.qvod.utils.RxUtil;
import com.zmj.qvod.utils.StatusBarUtils;
import com.zmj.qvod.utils.StringFormatUtil;
import com.zmj.qvod.view.activity.MovieDetailsActivity;
import com.zmj.qvod.view.activity.WebViewActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;
import rx.Observer;
import rx.Subscription;

/**
 * Created by matt on 2017/4/6.
 */
public class MovieDetailsPresenter extends BeamBasePresenter<MovieDetailsActivity> {

    private HotMovieBean.SubjectsBean positionData;
    //高斯模糊图高度
    private int imageBgHeight;
    //滑动到一定高度改为不透明
    private int slidingDistance;
    //
    private Subscription rxSubscription;
    //Adapter
    private MovieCastsAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(getView().tvMovieIntro.getText().toString())) {
            getView().llLoading.setStatus(LoadingLayout.Success);
        }
    }

    @Override
    protected void onCreateView(@NonNull MovieDetailsActivity view) {
        super.onCreateView(view);

        initParam();

        initView();

        initListener();

        initMeasure();

        initNewSlidingParams();

        initData();

        initAdapter();
    }

    private void initParam() {
        positionData = (HotMovieBean.SubjectsBean) getView().getIntent().getSerializableExtra("positionData");
    }

    private void initView() {
        ImageLoader.load(positionData.getImages().getLarge(), getView().ivMoviePic, 200);
        getView().getToolbar().setTitle(positionData.getTitle());
        //高斯模糊
        Glide.with(getView())
                .load(positionData.getImages().getLarge())
                .error(R.drawable.stackblur_default)
                .bitmapTransform(new BlurTransformation(getView(), 23, 4))
                .into(getView().ivToolbarBg);
        //高斯模糊
        Glide.with(getView())
                .load(positionData.getImages().getLarge())
                .error(R.drawable.stackblur_default)
                .bitmapTransform(new BlurTransformation(getView(), 23, 4))
                .into(getView().ivDimBg);
        //
        getView().tvDetailsTitle.setText("评分:" + positionData.getRating().getAverage() + "分(" + positionData.getCollect_count() + "人评分)");
        getView().tvDetailsDirect.setText("导演：" + StringFormatUtil.formatName(positionData.getDirectors()));
        getView().tvDetailsActor.setText("演员：" + StringFormatUtil.formatName(positionData.getCasts(), true));
        getView().tvDetailsType.setText("类型：" + StringFormatUtil.formatGenres(positionData.getGenres()));
        getView().tvDetailsDate.setText("上映日期：" + positionData.getYear());
    }

    private void initListener() {
        //finish
        getView().toolBar.setNavigationOnClickListener(view -> getView().onBackPressed());
        //
        getView().nsvTitle.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollChangeHeader(scrollY);
            }
        });
        getView().llLoading.setOnReloadListener(v -> {
            if (JUtils.isNetWorkAvilable())
                //onClick
                initData();
        });
        getView().toolBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.actionbar_more) {
                WebViewActivity.startAction(getView(), positionData.getAlt());
            }
            return false;
        });
    }

    /**
     * 计算图片偏移的高度
     */
    private void initMeasure() {
        // toolbar 的高
        int toolbarHeight = getView().toolBar.getLayoutParams().height;
        int headerBgHeight = toolbarHeight + AppUtils.getStatusBarHeight(getView());

        // 使背景图向上移动到图片的最低端，保留（titlebar+statusbar）的高度
        ViewGroup.LayoutParams params = getView().ivToolbarBg.getLayoutParams();
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams) getView().ivToolbarBg.getLayoutParams();
        int marginTop = params.height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);

        getView().ivToolbarBg.setImageAlpha(0);
        StatusBarUtils.setTranslucentImageHeader(getView(), 0, getView().toolBar);
        // 上移背景图片，使空白状态栏消失(这样下方就空了状态栏的高度)
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getView().ivDimBg.getLayoutParams();
        layoutParams.setMargins(0, -AppUtils.getStatusBarHeight(getView()), 0, 0);
        ViewGroup.LayoutParams imgItemBgparams = getView().ivDimBg.getLayoutParams();
        // 获得高斯图背景的高度
        imageBgHeight = imgItemBgparams.height;
    }

    private void initNewSlidingParams() {
        int titleBarAndStatusHeight = (int) (getView().getResources().getDimension(R.dimen.nav_bar_height) + AppUtils.getStatusBarHeight(getView()));
        // 减掉后，没到顶部就不透明了
        slidingDistance = imageBgHeight - titleBarAndStatusHeight - (int) (getView().getResources().getDimension(R.dimen.base_header_activity_slide_more));
    }

    /**
     * 根据页面滑动改变Toolbar背景
     *
     * @param scrollY
     */
    private void scrollChangeHeader(int scrollY) {
        if (scrollY < 0) scrollY = 0;
        //
        Drawable drawable = getView().ivToolbarBg.getDrawable();
        //
        if (drawable == null) return;
        //
        float alpha = Math.abs(scrollY) * 1.0f / (imageBgHeight);
        if (scrollY <= slidingDistance) {
            // title部分的渐变
            drawable.mutate().setAlpha((int) (alpha * 255));
            getView().ivToolbarBg.setBackground(drawable);
        } else {
            drawable.mutate().setAlpha(255);
            getView().ivToolbarBg.setBackground(drawable);
        }
    }

    /**
     * 请求详情数据
     */
    private void initData() {
        rxSubscription = HttpUtils.getInstance().getMovieServer().getMovieDetail(positionData.getId())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<MovieDetailBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        getView().recyclerView.getSwipeToRefresh().setRefreshing(false);
                        getView().recyclerView.showError();
                    }

                    @Override
                    public void onNext(MovieDetailBean movieDetailBean) {
                        transformData(movieDetailBean);
                        getView().llLoading.setStatus(LoadingLayout.Success);
                    }
                });
    }

    /**
     * 初始化列表
     */
    private void initAdapter() {
        adapter = new MovieCastsAdapter(getView());
        getView().recyclerView.setLayoutManager(new LinearLayoutManager(getView()));
        getView().recyclerView.setAdapterWithProgress(adapter);
        getView().recyclerView.getRecyclerView().setNestedScrollingEnabled(false);
    }

    /**
     * 异步线程转换数据
     */
    private void transformData(MovieDetailBean movieDetailBean) {
        new Thread(() -> {
            for (int i = 0; i < movieDetailBean.getDirectors().size(); i++) {
                MovieDetailBean.DirectorsBean directorsBean = movieDetailBean.getDirectors().get(i);
//                    movieDetailBean.getDirectors().get(i).setType("导演");
                MovieDetailBean.CastsBean castsBean = new MovieDetailBean.CastsBean();
                castsBean.setAlt(directorsBean.getAlt());
                //
                MovieDetailBean.CastsBean.AvatarsBean avatarsBean = new MovieDetailBean.CastsBean.AvatarsBean();
                avatarsBean.setLarge(directorsBean.getAvatars().getLarge());
                castsBean.setAvatars(avatarsBean);
                //
                castsBean.setId(directorsBean.getId());
                castsBean.setName(directorsBean.getName());
                castsBean.setType("导演");
                movieDetailBean.getCasts().add(0, castsBean);
            }
            for (int i = 0; i < movieDetailBean.getCasts().size(); i++) {
                if (TextUtils.isEmpty(movieDetailBean.getCasts().get(i).getType()))
                    movieDetailBean.getCasts().get(i).setType("演员");
            }
            MovieDetailsPresenter.this.getView().runOnUiThread(() -> setMovieDetails(movieDetailBean));
        }).start();
    }

    /**
     * 显示剧情简介和演员表
     */
    private void setMovieDetails(MovieDetailBean movieDetailBean) {
        //设置演员表
        adapter.clear();
        adapter.addAll(movieDetailBean.getCasts());
        //设置其它信息
        getView().tvDetailsCountry.setText("制片国家/地区：" + movieDetailBean.getCountries());
        getView().tvMovieCall.setText(movieDetailBean.getAka().toString());
        getView().tvMovieIntro.setText(movieDetailBean.getSummary());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxSubscription.unsubscribe();
    }
}
