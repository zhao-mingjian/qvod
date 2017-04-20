package com.zmj.qvod.module.service;

import com.zmj.qvod.module.bean.HotMovieBean;
import com.zmj.qvod.module.bean.MovieDetailBean;
import com.zmj.qvod.module.bean.PicBean;
import com.zmj.qvod.module.bean.VideoHttpResponse;
import com.zmj.qvod.module.bean.VideoRes;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by matt on 2017/3/10.
 */

public interface RetrofitHttpClient {

    /**
     * 首页
     *
     * @return
     */
    @POST("/homePageApi/homePage.do")
    Observable<VideoHttpResponse<VideoRes>> getIndexPage();

    /**
     * 影片分类列表
     *
     * @param catalogId 分类Id
     * @param pnum      分页
     * @return
     */
    @GET("/columns/getVideoList.do")
    Observable<VideoHttpResponse<VideoRes>> getVideoList(@Query("catalogId") String catalogId, @Query("pnum") String pnum);

    /**
     * 影片分类列表点击详情
     *
     * @param mediaId 影片Id
     * @return
     */
    @GET("/videoDetailApi/videoDetail.do")
    Observable<VideoHttpResponse<VideoRes>> getVideoInfo(@Query("mediaId") String mediaId);

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 豆瓣高分榜
     *
     * @return
     */
    @GET("/v2/movie/in_theaters")
    Observable<HotMovieBean> getHotMovie();

    /**
     * 豆瓣高分电影榜
     *
     * @param start 从多少开始，如从"0"开始
     * @param count 一次请求的数目，如"10"条，最多100
     * @return
     */
    @GET("/v2/movie/top250")
    Observable<HotMovieBean> getMovieTop(@Query("start") int start, @Query("count") int count);

    /**
     * 获取电影详情
     *
     * @param id 电影bean里的id
     */
    @GET("/v2/movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") String id);

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 图片
     *
     * @param reqType
     * @param reqFrom
     * @param query   内容
     * @param start   分页
     * @return
     */
    @GET("/pics")
    Observable<PicBean> getPicture(@Query("reqType") String reqType, @Query("reqFrom") String reqFrom,
                                   @Query("query") String query, @Query("start") int start);
}
