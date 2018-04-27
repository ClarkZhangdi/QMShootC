//package com.amiba.frame.androidframe.sophix;
//
//import android.content.Context;
//import android.support.annotation.Keep;
//
//import com.amiba.frame.androidframe.base.BaseApplication;
//import com.amiba.frame.androidframe.util.AppConstant;
//import com.amiba.frame.androidframe.util.PhoneUtils;
//import com.amiba.frame.androidframe.util.log.DebugLog;
//import com.taobao.sophix.PatchStatus;
//import com.taobao.sophix.SophixApplication;
//import com.taobao.sophix.SophixEntry;
//import com.taobao.sophix.SophixManager;
//import com.taobao.sophix.listener.PatchLoadStatusListener;
//
///**
// * com.amiba.frame.androidframe.sophix
// * Created by wudl on 17/12/2.
// */
//
//public class SophixStubApplication extends SophixApplication {
//
////    private final String TAG = "SophixStubApplication";
////
////    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
////    @Keep
////    @SophixEntry(BaseApplication.class)
////    static class RealApplicationStub {}
////    @Override
////    protected void attachBaseContext(Context base) {
////        super.attachBaseContext(base);
//////         如果需要使用MultiDex，需要在此处调用。
//////         MultiDex.install(this);
////        initSophix();
////    }
////    private void initSophix() {
////        String appVersion = PhoneUtils.getAppVersionName(this);
////        final SophixManager instance = SophixManager.getInstance();
////        instance.setContext(this)
////                .setAppVersion(appVersion)
////                .setSecretMetaData(AppConstant.idSecret, AppConstant.appSecret, AppConstant.rsaSecret)
////                .setEnableDebug(AppConstant.ISDEBUG)
////                .setEnableFullLog()
////                .setAesKey(null)//用户自定义aes秘钥, 会对补丁包采用对称加密
////                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
////                    @Override
////                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
////                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
////                            DebugLog.i(TAG, "sophix load patch success!");
////                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
////                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
////                            DebugLog.i(TAG, "sophix preload patch success. restart app to make effect.");
//////                            .killProcessSafely();
////                        }
////                    }
////                }).initialize();
////    }
//}
