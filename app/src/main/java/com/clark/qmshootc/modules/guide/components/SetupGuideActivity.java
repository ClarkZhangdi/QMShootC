package com.clark.qmshootc.modules.guide.components;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.amiba.frame.androidframe.base.BaseActivity;
import com.clark.qmshootc.R;
import com.clark.qmshootc.common.utils.app.FullScreenUtils;
import com.clark.qmshootc.modules.guide.presenter.ISetupGuidePresenter;
import com.clark.qmshootc.modules.guide.presenter.impl.SetupGuidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetupGuideActivity extends BaseActivity implements ISetupGuidePresenter.View {

    @BindView(R.id.id_vp_guidePic)
    ViewPager idVpGuidePic;

    ISetupGuidePresenter mSetupGuidePresenter;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_setup_guide;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup contentFrameLayout = findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null) {
            parentView.setFitsSystemWindows(false);
        }
        FullScreenUtils.FullScreen(this, true);
        ButterKnife.bind(this);
        mSetupGuidePresenter = new SetupGuidePresenter(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FullScreenUtils.FullScreen(this, true);
    }

    /**
     * 设置Activity的statusBar隐藏
     *
     * @param activity
     */
    public static void statusBarHide(AppCompatActivity activity) {
        // 代表 5.0 及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            return;
        }

        // versionCode > 4.4  and versionCode < 5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }


    /**
     * 设置是否隐藏导航栏
     *
     * @param activity
     * @param
     */
    public static void setNavigationBar(AppCompatActivity activity, int visible) {
        View decorView = activity.getWindow().getDecorView();
        //显示NavigationBar
        if (View.GONE == visible) {
            int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    public ViewPager getViewPager() {
        return idVpGuidePic;
    }

}
