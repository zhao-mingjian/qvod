package com.zmj.qvod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.zmj.qvod.R;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.VideoInfo;
import com.zmj.qvod.view.activity.VideoInfoActivity;

import java.util.List;

/**
 * Created by zmj on 2016/2/6 0006.
 */
public class ImageLoopAdapter extends DynamicPagerAdapter {

    List<VideoInfo> banners;

    @Override
    public View getView(ViewGroup container, int position) {
        View views = LayoutInflater.from(container.getContext()).inflate(R.layout.view_roll_viewpager, null);
        ImageView imageView = (ImageView) views.findViewById(R.id.iv_roll_viewpager);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.load(banners.get(position).pic, imageView, 320);
        imageView.setOnClickListener(view -> {
            VideoInfoActivity.actionStart(container.getContext(), banners.get(position));
        });
        return views;
    }

    @Override
    public int getCount() {
        if (banners != null) {
            return banners.size();
        } else {
            return 0;
        }
    }

    public void setBanners(List<VideoInfo> banners) {
        this.banners = banners;
    }
}
