package com.amiba.frame.androidframe.network.callback;

import com.amiba.frame.androidframe.util.gson.GsonUtil;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * com.amiba.frame.androidframe.network.callback
 * Created by wudl on 18/1/5.
 */

public abstract class JsonCallback<T> extends Callback<T> {

    private Class<T> clazz;
    private Type type;

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws IOException {
        if (response != null && response.body() != null) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String json = responseBody.string();
                if (clazz != null) {
                    return GsonUtil.toBean(json, clazz);
                } else if (type != null) {
                    return GsonUtil.toBean(json, type);
                }
            }
        }
        return null;
    }
}
