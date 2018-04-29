package com.clark.qmshootc.modules.guide.components;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

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
        FullScreenUtils.setFitsSystem(this);
        FullScreenUtils.FullScreen(this, true);
        mSetupGuidePresenter = new SetupGuidePresenter(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FullScreenUtils.FullScreen(this, hasFocus);
    }

    /**
     * 为了界面切换时不会变得突兀,在onPause的时候调用
     * FullScreenUtils.FullScreen(this);方法让导航栏变透明不然会导致被隐藏的状态栏入突然出现
     */
    @Override
    protected void onPause() {
        super.onPause();
        FullScreenUtils.FullScreen(this);
    }

    @Override
    public ViewPager getViewPager() {
        return idVpGuidePic;
    }

}
