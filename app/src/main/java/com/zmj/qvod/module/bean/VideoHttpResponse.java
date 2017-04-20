package com.zmj.qvod.module.bean;

/**
 * Created by codeest on 16/8/28.
 */

public class VideoHttpResponse<T> {

    private int code;
    private String msg;
    T ret;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getRet() {
        return ret;
    }

    public void setRet(T ret) {
        this.ret = ret;
    }
}
