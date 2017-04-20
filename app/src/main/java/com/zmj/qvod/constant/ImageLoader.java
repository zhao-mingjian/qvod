package com.zmj.qvod.constant;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.zmj.qvod.R;
import com.zmj.qvod.app.VideoApplication;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Description: ImageLoader
 * Creator: zmj
 * date: 2017/02/21 9:53
 */
public class ImageLoader {

    public static void load(Context context, String url, ImageView iv) {    //使用Glide加载ImageView(如头像)时，不要使用占位图
        Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void load(Activity activity, String url, ImageView iv) {    //使用Glide加载ImageView(如头像)时，不要使用占位图
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void loadAll(Context context, String url, ImageView iv) {    //不缓存，全部从网络加载
        Glide.with(context).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadAll(Activity activity, String url, ImageView iv) {    //不缓存，全部从网络加载
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
        }
    }

    public static void load(String url, ImageView iv, int type) {    //使用占位图
        switch (type) {
            case 320:
                Glide.with(VideoApplication.getInstance()).load(url).crossFade(1500).placeholder(R.drawable.default_320).into(iv);
                break;
            case 200:
                Glide.with(VideoApplication.getInstance()).load(url).crossFade(1500).placeholder(R.drawable.default_200).into(iv);
                break;
            case 100:
                Glide.with(VideoApplication.getInstance()).load(url).placeholder(R.drawable.pic_default).into(iv);
                break;
            default:
                break;
        }
    }

    /**
     * Glide 获得图片缓存路径
     */
    public static String getImagePath(String imgUrl) {
        String path = null;
        FutureTarget<File> future = Glide.with(VideoApplication.getInstance())
                .load(imgUrl)
                .downloadOnly(500, 500);
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }
}
