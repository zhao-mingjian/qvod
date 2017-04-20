package com.zmj.qvod.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.utils.JUtils;
import com.zmj.qvod.adapter.ImageBrowseAdapter;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.PicBean;
import com.zmj.qvod.utils.FileUtils;
import com.zmj.qvod.view.activity.ImageBrowseActivity;

import java.util.ArrayList;

/**
 * Created by matt on 2017/4/18.
 */

public class ImageBrowsePresenter extends BeamBasePresenter<ImageBrowseActivity> {

    private ArrayList<PicBean.ItemsBean> items;
    private int mPosition;

    @Override
    protected void onCreateView(@NonNull ImageBrowseActivity view) {
        super.onCreateView(view);

        initPrams();

        initImageLoader();
    }

    private void initPrams() {
        items = (ArrayList<PicBean.ItemsBean>) getView().getIntent().getSerializableExtra("items");
        mPosition = getView().getIntent().getIntExtra("position", 0);
    }

    private void initImageLoader() {
        ImageBrowseAdapter browseAdapter = new ImageBrowseAdapter(getView());
        browseAdapter.setItems(items);
        getView().vpImage.setAdapter(browseAdapter);
        getView().vpImage.setCurrentItem(mPosition);
        if (items != null && items.size() != 0) {
            getView().tvImage.setText((mPosition + 1) + "/" + items.size());
        }
        getView().vpImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                if (items != null && items.size() != 0) {
                    getView().tvImage.setText((position + 1) + "/" + items.size());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 保存图片至SD卡
     */
    public void saveImageSdCard() {
        JUtils.Toast("正在保存图片···");
        new Thread(() -> {
            BitmapFactory.Options Options = new BitmapFactory.Options();
            String cachePath = ImageLoader.getImagePath(items.get(mPosition).getPic_url());
            if (cachePath == null) {
                cachePath = ImageLoader.getImagePath(items.get(mPosition).getThumbUrl());
            }
            String finalCachePath = cachePath;
            getView().runOnUiThread(() -> {
                Bitmap bitmap = BitmapFactory.decodeFile(finalCachePath, Options);
                if (bitmap == null) return;
                FileUtils.saveImageToGallery(getView(), bitmap);
                JUtils.Toast("图片已保存至" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/Qvod");
            });
        }).start();

    }

}
