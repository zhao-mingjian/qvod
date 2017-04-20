package com.zmj.qvod.module.service;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit.Ok3Client;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by matt on 2017/3/13.
 */

public class HttpUtils {

    private static final RestAdapter.LogLevel LOG_LEVEL = RestAdapter.LogLevel.NONE;
    //API
    private static final String VIDEO_HOST = "https://api.svipmovie.com/front";
    private static final String MOVIE_HOST = "https://api.douban.com";
    private static final String PIC_HOST = "https://pic.sogou.com";
    //
    private static RetrofitHttpClient mVideoClient;
    private static RetrofitHttpClient mMovieClient;
    private static RetrofitHttpClient mPicClient;
    //
    private Gson gson;
    private Context mContext;
    private static HttpUtils mHttpUtils;

    public void setContext(Context context) {
        this.mContext = context;
    }

    public static HttpUtils getInstance() {
        if (mHttpUtils == null) {
            mHttpUtils = new HttpUtils();
        }
        return mHttpUtils;
    }

    /**
     * 电影
     *
     * @return
     */
    public RetrofitHttpClient getVideoServer() {
        if (mVideoClient == null) {
            mVideoClient = getRestAdapter(VIDEO_HOST).create(RetrofitHttpClient.class);
        }
        return mVideoClient;
    }

    /**
     * 豆瓣
     *
     * @return
     */
    public RetrofitHttpClient getMovieServer() {
        if (mMovieClient == null) {
            mMovieClient = getRestAdapter(MOVIE_HOST).create(RetrofitHttpClient.class);
        }
        return mMovieClient;
    }

    /**
     * 图片
     *
     * @return
     */
    public RetrofitHttpClient getPicServer() {
        if (mPicClient == null) {
            mPicClient = getRestAdapter(PIC_HOST).create(RetrofitHttpClient.class);
        }
        return mPicClient;
    }

    private RestAdapter getRestAdapter(String HOST) {
        File cacheFile = new File(mContext.getApplicationContext().getCacheDir().getAbsolutePath(), "videoCache");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(cacheFile, cacheSize);
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.cache(cache);
        okBuilder.readTimeout(20, TimeUnit.SECONDS);//设置读取新连接超时
        okBuilder.connectTimeout(10, TimeUnit.SECONDS);//设置新连接的默认连接超时
        okBuilder.writeTimeout(20, TimeUnit.SECONDS);//设置默认为新连接编写超时
        OkHttpClient client = okBuilder.build();
        //
        RestAdapter.Builder restBuilder = new RestAdapter.Builder();
        restBuilder.setClient(new Ok3Client(client));
        restBuilder.setEndpoint(HOST);//URL_HOST
        restBuilder.setConverter(new GsonConverter(getGson()));//解析
        //
        RestAdapter videoRestAdapter = restBuilder.build();
        videoRestAdapter.setLogLevel(LOG_LEVEL);
        return videoRestAdapter;
    }

    private Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setFieldNamingStrategy(new AnnotateNaming());
            gsonBuilder.serializeNulls();
            gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
            gson = gsonBuilder.create();
        }
        return gson;
    }

    private static class AnnotateNaming implements FieldNamingStrategy {
        @Override
        public String translateName(Field field) {
            ParamNames a = field.getAnnotation(ParamNames.class);
            return a != null ? a.value() : FieldNamingPolicy.IDENTITY.translateName(field);
        }
    }


}
