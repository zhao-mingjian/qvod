package com.zmj.qvod.utils;

import com.zmj.qvod.module.bean.VideoInfo;
import com.zmj.qvod.module.bean.VideoRes;
import com.zmj.qvod.module.bean.VideoType;

/**
 * Description: BeanUtil
 * Creator: yxc
 * date: 2016/9/21 14:39
 */
public class BeanUtil {


    public static VideoInfo VideoType2VideoInfo(VideoType videoType) {
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.title = videoType.title;
        videoInfo.dataId = videoType.dataId;
        videoInfo.pic = videoType.pic;
        videoInfo.airTime = videoType.airTime;
        videoInfo.score = videoType.score;
        return videoInfo;
    }

    public static VideoRes VideoInfo2VideoRes(VideoInfo videoInfo) {
        VideoRes videoRes = new VideoRes();
        videoRes.title = StringUtils.isEmpty(videoInfo.title);
        videoRes.pic = StringUtils.isEmpty(videoInfo.pic);
        videoRes.score = StringUtils.isEmpty(videoInfo.score);
        videoRes.airTime = StringUtils.isEmpty(videoInfo.airTime);
        return videoRes;
    }
}
