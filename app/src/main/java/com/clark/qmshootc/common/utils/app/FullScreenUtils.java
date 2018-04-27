package com.clark.qmshootc.common.utils.app;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.amiba.frame.androidframe.ui.sysbar.SystemBarTintManager;

import static com.amiba.frame.androidframe.util.PhoneUtils.getStatusBarHeight;

/**
 * create by SuperClark 2018/4/26
 */
public class FullScreenUtils {

    /**
     * 该方法只能在onWindowFocusChange方法中调用
     * 确认当前模式为全屏状态
     *
     * @param activity
     * @param hasFocus
     */
    public static void FullScreen(AppCompatActivity activity, Boolean hasFocus) {
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
    }

    /**
     * 该方法令指定Activity的状态栏为透明状态,
     * 导航栏默认不显示,滑出导航栏时导航栏会暂时显示,稍后自动隐藏
     *
     * @param activity
     */
    public static void FullScreen(AppCompatActivity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }

}