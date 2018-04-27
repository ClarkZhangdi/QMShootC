/**
 * Copyright  2015
 * All right reserved
 *
 * @Name: SystemBarTintUtil.java
 * @Author: Wudl
 * @Date: 2015年11月13日
 * @Description:
 */
package com.amiba.frame.androidframe.ui.sysbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.amiba.frame.androidframe.util.PhoneUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.amiba.frame.androidframe.util.PhoneUtils.getStatusBarHeight;


/**
 * Copyright  2015
 * All right reserved
 *
 * @Name: SystemBarTintUtil.java
 * @Author: Wudl
 * @Date: 2015年11月13日
 * @Description:
 */
public class SystemBarTintUtil {

    /**
     * @param mActivity
     * @param resource
     * @Description 设置状态栏的颜色
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static void initSystemTint(Activity mActivity, @ColorRes int resource, boolean enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(mActivity, true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(resource);
    }

    public static void setSystemTintColor(Activity mActivity, int color, boolean enabled) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(mActivity, true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(color);
    }

    public static void setStateBarAlpha(Activity mActivity, float alpha) {
        SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarAlpha(alpha);
//		tintManager.setTintAlpha(alpha);
    }

    public static void setSystemTint(Activity mActivity, int color) {
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(mActivity,true);
		}*/

//		SystemBarTintUtil.setStateBarTransParent(mActivity);
        SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(color);
    }

    /**
     * 将界面内容延伸到状态栏下面
     *
     * @param activity
     */
    public static void setStateBarTransParent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }

    /**
     * @param mActivity
     * @param on
     * @Author wudl
     * @Description 设置状态栏是否透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void setTranslucentStatus(Activity mActivity, boolean on) {
        Window win = mActivity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * @param activity
     * @param darkmode
     * @Description 设置状态栏的字体颜色为深色
     */
    public static void setStatsBarFont(Activity activity, boolean darkmode) {
        String band = PhoneUtils.getBrand();
        if (TextUtils.isEmpty(band)) return;
        BandName bandName;
        if (band.equalsIgnoreCase("meizu")) {
            bandName = BandName.Meizu;
        } else if (band.equalsIgnoreCase("xiaomi")) {
            bandName = BandName.Xiaomi;
        } else {
            bandName = BandName.Android;
        }

        switch (bandName) {
            case Meizu:
                setMeizuStatusBarDarkIcon(activity, darkmode);
                break;
            case Xiaomi:
                setMiuiStatusBarDarkMode(activity, darkmode);
                break;
            case Android:
                setAndroidFontDark(activity);
                break;
        }
    }

    /**
     * @param activity
     * @param darkmode
     * @return
     * @Author wudl
     * @Description 小米系统MiUi的状态栏字体颜色值
     */
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param activity
     * @param dark
     * @return
     * @Author wudl
     * @Description 魅族系统Flyme的状态栏字体颜色值
     */
    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * @param activity
     * @Author wudl
     * @Description android6.0及以上系统的状态栏字体颜色值
     */
    public static void setAndroidFontDark(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public enum BandName {
        Meizu,
        Xiaomi,
        Android
    }

    public static void recoverStatusBar(Activity activity) {
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.removeStatusBarView(activity);
    }

    public static View getActionBarView(final Activity activity) {
        final Window window = activity.getWindow();
        final View v = window.getDecorView();
        final int resId = activity.getResources().getIdentifier("action_bar_container", "id", "android");
        return v.findViewById(resId);
    }


    public static void setStatusHegihtLayout(Activity mActivity, View view){
        int statusHeight = getStatusBarHeight(mActivity);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) view.getLayoutParams();
            params.height = statusHeight;
            view.setLayoutParams(params);
        }
    }
}
