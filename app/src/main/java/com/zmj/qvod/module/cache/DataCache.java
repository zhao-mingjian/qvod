package com.zmj.qvod.module.cache;

import android.content.Context;
import android.util.LruCache;

import com.zmj.qvod.module.bean.VideoRes;
import com.zmj.qvod.utils.FileUtils;

import java.io.File;

import okhttp3.internal.DiskLruCache;

/**
 * Created by matt on 2017/3/15.
 */

public class DataCache {

    DiskLruCache mDiskLruCache = null;

    /**
     * 磁盘缓存
     * @param context
     */
    public void getDiskCache(Context context) {
        File cacheDir = FileUtils.getDiskCacheDir(context, "object");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        //...
    }

    /**
     * 内存缓存
     */
    private LruCache<String, VideoRes> mVideoCache;


    /**
     * 添加进入缓存列表
     *
     * @param key
     * @param value
     */
    public void addVideoCache(String key, VideoRes value) {
        mVideoCache.put(key, value);
    }

    /**
     * 从缓存列表中拿出来
     *
     * @param key
     * @return
     */
    public VideoRes getVideoCache(String key) {
        return mVideoCache.get(key);
    }
}
