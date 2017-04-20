package com.zmj.qvod.event;

/**
 * Created by matt on 2017/3/28.
 */

public class VideoEvent {

    /**
     * 控制FLOATING是否显示
     */
    public static final int VIDEO_FLOATING = 881;

    /////////////////////////////////////////////////
    /**
     * 设置消息的类型
     */
    public int type;

    /**
     * 设置传递的对象
     */
    public Object obj;

    public VideoEvent setType(int type) {
        this.type = type;
        return this;
    }

    public VideoEvent setObj(Object obj) {
        this.obj = obj;
        return this;
    }


}
