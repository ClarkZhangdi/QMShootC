package com.amiba.frame.androidframe.network.builder;


import com.amiba.frame.androidframe.network.OkHttpUtils;
import com.amiba.frame.androidframe.network.request.OtherRequest;
import com.amiba.frame.androidframe.network.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
