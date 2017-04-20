package com.zmj.qvod.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JUtils;
import com.zmj.qvod.R;
import com.zmj.qvod.app.VideoApplication;
import com.zmj.qvod.module.bean.PicBean;
import com.zmj.qvod.utils.StringUtils;

/**
 * Created by matt on 2017/4/17.
 */
public class PictureViewHolder extends BaseViewHolder<PicBean.ItemsBean> {

    private float screenWidth;
    private ImageView imageView;

    public PictureViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_pic);
        imageView = $(R.id.iv_pic);
        screenWidth = JUtils.getScreenWidth() / 2;
    }

    @Override
    public void setData(PicBean.ItemsBean data) {
        super.setData(data);
        float height = Float.parseFloat(data.getHeight());
        float width = Float.parseFloat(data.getWidth());
        int widthPx = (int) screenWidth / 2;
        int heightPx = (int) ((height / width) * screenWidth);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = heightPx;
        imageView.setLayoutParams(layoutParams);
        //
        int widthI = (widthPx / 2) > 0 ? widthPx / 2 : 150;
        int heightI = (heightPx / 2) > 0 ? heightPx / 2 : 150;
        Glide.with(VideoApplication.getInstance())
                .load(StringUtils.isEmpty(data.getThumbUrl()))
                .crossFade(1500)
                .override(widthI, heightI)
                .placeholder(R.drawable.pic_default)
                .into(imageView);
    }
}
