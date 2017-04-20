package com.zmj.qvod.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.zmj.qvod.R;
import com.zmj.qvod.module.bean.PicBean;
import com.zmj.qvod.presenter.ImageBrowsePresenter;
import com.zmj.qvod.utils.AppUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by matt on 2017/4/18.
 */
@RequiresPresenter(ImageBrowsePresenter.class)
public class ImageBrowseActivity extends BeamBaseActivity<ImageBrowsePresenter> {

    @BindView(R.id.vp_image)
    public ViewPager vpImage;
    @BindView(R.id.tv_image)
    public TextView tvImage;
    @BindView(R.id.tv_image_save)
    TextView tvImageSave;

    public static void startAction(Context context, ArrayList<PicBean.ItemsBean> items, int position) {
        Intent intent = new Intent(context, ImageBrowseActivity.class);
        intent.putExtra("items", items);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_browse);
        ButterKnife.bind(this);
        AppUtils.steepStatusBar(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //设置finish页面无效果
        overridePendingTransition(0, 0);
    }

    @OnClick(R.id.tv_image_save)
    public void onViewClicked() {
        getPresenter().saveImageSdCard();
        //防止出现连续点击保存多张图片
        tvImageSave.setEnabled(false);
        new Handler().postDelayed(() ->
                        tvImageSave.setEnabled(true)
                , 200);
    }

}
