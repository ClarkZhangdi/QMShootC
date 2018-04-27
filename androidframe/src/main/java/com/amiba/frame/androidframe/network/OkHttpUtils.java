package com.amiba.frame.androidframe.network;


import android.text.TextUtils;

import com.amiba.frame.androidframe.network.builder.GetBuilder;
import com.amiba.frame.androidframe.network.builder.HeadBuilder;
import com.amiba.frame.androidframe.network.builder.OtherRequestBuilder;
import com.amiba.frame.androidframe.network.builder.PostFileBuilder;
import com.amiba.frame.androidframe.network.builder.PostFormBuilder;
import com.amiba.frame.androidframe.network.builder.PostStringBuilder;
import com.amiba.frame.androidframe.network.callback.Callback;
import com.amiba.frame.androidframe.network.request.RequestCall;
import com.amiba.frame.androidframe.network.utils.Platform;
import com.amiba.frame.androidframe.util.log.DebugLog;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Executor;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 *
 */
public class OkHttpUtils {
    public static final String TAG = OkHttpUtils.class.getSimpleName();

    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static final MediaType FROM_URLENCODED
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    public static final MediaType MULTI_DATA
            = MediaType.parse("multipart/form-data");

    public static final MediaType TEXT_XML
            = MediaType.parse("text/xml");

    public OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        } else {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = Platform.get();
    }


    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance() {
        return initClient(null);
    }


    public Executor getDelivery() {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public static OtherRequestBuilder put() {
        return new OtherRequestBuilder(METHOD.PUT);
    }

    public static HeadBuilder head() {
        return new HeadBuilder();
    }

    public static OtherRequestBuilder delete() {
        return new OtherRequestBuilder(METHOD.DELETE);
    }

    public static OtherRequestBuilder patch() {
        return new OtherRequestBuilder(METHOD.PATCH);
    }

    public void execute(final RequestCall requestCall, Callback callback) {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;
        final int id = requestCall.getOkHttpRequest().getId();

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailResultCallback(call, e, finalCallback, id);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(call, new IOException("Canceled!"), finalCallback, id);
                        return;
                    }

                    if (!finalCallback.validateReponse(response, id)) {
                        sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback, id);
                        return;
                    }

                    Object o = finalCallback.parseNetworkResponse(response, id);
                    sendSuccessResultCallback(o, finalCallback, id);
                } catch (Exception e) {
                    sendFailResultCallback(call, e, finalCallback, id);
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }

            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final Callback callback, final int id) {
        if (callback == null) return;

        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback, final int id) {
        if (callback == null) return;
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object, id);
                callback.onAfter(id);
            }
        });
    }

    /**
     * 拼接url参数
     *
     * @param url
     * @param params
     */
    public static String getUrl(String url, Map<String, String> params) {
        if (TextUtils.isEmpty(url))
            return null;
        StringBuilder sb = null;
        try {
            if (url.contains("?")) {
                sb = new StringBuilder(url);
            } else {
                sb = new StringBuilder(url + "?");
            }
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    sb.append(entry.getKey())
                            .append('=')
                            .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                            .append('&');
                    String tmp = URLEncoder.encode(entry.getValue(), "UTF-8");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            DebugLog.i(TAG, "URL：" + sb.toString());
        } catch (Exception e) {
            DebugLog.e(TAG, " " + e.getMessage());
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public static class METHOD {
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
    }
}

