package com.zmj.qvod.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.zmj.qvod.R;
import com.zmj.qvod.constant.ImageLoader;
import com.zmj.qvod.module.bean.PicBean;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by zmj on 2016/2/6 0006.
 */
public class ImageBrowseAdapter extends DynamicPagerAdapter {

    private ArrayList<PicBean.ItemsBean> items;

    private Activity activity;

    public ImageBrowseAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        View contentView = LayoutInflater.from(container.getContext()).inflate(R.layout.view_photo, null);
        PhotoView imageView = ButterKnife.findById(contentView, R.id.photo_view);
        LinearLayout loadingView = ButterKnife.findById(contentView, R.id.ll_photo);

        //手势控件
        Glide.with(container.getContext())
                .load(items.get(position).getPic_url())
                .asBitmap()
                .placeholder(R.drawable.pic_default)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        loadingView.setVisibility(View.GONE);
                        ImageLoader.load(items.get(position).getThumbUrl(), imageView, 100);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(new SimpleTarget<Bitmap>(480, 800) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        imageView.setImageBitmap(bitmap);
                        loadingView.setVisibility(View.GONE);
                    }

                });
        //
        imageView.setOnClickListener(view -> activity.finish());
        return contentView;
    }

    @Override
    public int getCount() {
        if (items == null) return 0;
        return items.size();
    }


    public void setItems(ArrayList<PicBean.ItemsBean> items) {
        this.items = items;
    }
}
