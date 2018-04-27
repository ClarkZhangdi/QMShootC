package com.amiba.frame.androidframe.base;


public interface TaskCallBack<T> {
    enum CallBackError {
        CONNECTION_TIMEOUT,    //链接超时
        OTHER_EXCEPTION //其他错误
    }

    void callBackResult(T t);

    void callBackResult(T t, int code);

    void callbackError(CallBackError error);

}