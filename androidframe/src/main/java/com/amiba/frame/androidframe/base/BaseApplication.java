package com.amiba.frame.androidframe.base;

import android.app.Application;

import com.amiba.frame.androidframe.network.OkHttpUtils;
import com.amiba.frame.androidframe.network.cookie.CookieJarImpl;
import com.amiba.frame.androidframe.network.cookie.store.MemoryCookieStore;
import com.amiba.frame.androidframe.network.https.HttpsUtils;
import com.facebook.stetho.Stetho;

import okhttp3.OkHttpClient;

/**
 * com.amiba.frame.androidframe.base
 * Created by wudl on 17/9/7.
 */

public class BaseApplication extends Application {

    public BaseApplication() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        //SophixManager.getInstance().queryAndLoadNewPatch();

//        有特殊要求的Okhttp在这里配置
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null,null, null);
        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar1)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

}
