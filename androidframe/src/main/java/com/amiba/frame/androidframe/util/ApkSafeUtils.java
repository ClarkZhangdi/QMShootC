package com.amiba.frame.androidframe.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.widget.Toast;

import com.amiba.frame.androidframe.util.log.DebugLog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 该工具类用于验证apk的安全性，比如：验证apk签名、apk包名、apk存放路径、apk是否已经安装
 * com.android.base.androidframework.common.util
 * Created by wudl on 16/12/21.
 */
public class ApkSafeUtils {

    /**
     * install sucess
     */
    protected static final int SUCCESS = 0;
    /**
     * SIGNATURES_INVALIDATE
     */
    protected static final int SIGNATURES_INVALIDATE = 3;
    /**
     * SIGNATURES_NOT_SAME
     */
    protected static final int VERIFY_SIGNATURES_FAIL = 4;
    /**
     * is needcheck
     */
    private static final boolean NEED_VERIFY_CERT = true;


    /**
     * 获取已安装的apk签名
     *
     * @param context
     * @param packageName
     * @return
     */
    public static Signature getPackageSignature(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm.getInstalledPackages(PackageManager.GET_SIGNATURES);
        for (PackageInfo info : apps) {
            if (info.packageName.equals(packageName)) {
                return info.signatures[0];
            }
        }
        return null;
    }

    /**
     * checkPagakgeName
     *
     * @param context
     * @param srcNewFile
     * @return
     */
    public static boolean checkPagakgeName(Context context, String srcNewFile) {
        PackageInfo packageInfo = context.getPackageManager().getPackageArchiveInfo(srcNewFile, PackageManager.GET_ACTIVITIES);

        if (packageInfo != null) {
            DebugLog.i("ApkSafeUtils", context.getPackageName());
            return TextUtils.equals(context.getPackageName(), packageInfo.packageName);
        }

        return false;
    }

    /**
     * checkFile
     *
     * @param aPath   文件路径
     * @param context context
     */
    public static boolean checkFile(String aPath, Context context) {
        File aFile = new File(aPath);
        if (aFile == null || !aFile.exists()) {
            Toast.makeText(context, "安装包已被恶意软件删除", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (context == null) {
            Toast.makeText(context, "安装包异常", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * 判断apk是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

}
