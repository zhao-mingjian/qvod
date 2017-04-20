package com.zmj.qvod.module.service;

/**
 * 自定义异常
 */
public class ApiException extends Exception {
    public ApiException(String msg)
    {
        super(msg);
    }
}
