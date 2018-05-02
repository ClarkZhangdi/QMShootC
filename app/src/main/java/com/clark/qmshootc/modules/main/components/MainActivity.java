package com.clark.qmshootc.modules.main.components;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amiba.frame.androidframe.base.BaseActivity;
import com.amiba.frame.androidframe.ui.pulltorefresh.library.PullToRefreshRecyclerView;
import com.clark.qmshootc.R;
import com.clark.qmshootc.common.utils.app.FullScreenUtils;
import com.clark.qmshootc.modules.main.presenter.IMainPresenter;
import com.clark.qmshootc.modules.main.presenter.impl.MainPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainPresenter.View {
    @BindView(R.id.id_refresh_recycler_view_main)
    PullToRefreshRecyclerView idRefreshRecyclerViewMain;
    @BindView(R.id.id_ll_title)
    LinearLayout idLlTitle;
    @BindView(R.id.id_iv_search)
    ImageView idIvSearch;
    @BindView(R.id.id_tv_search)
    TextView idTvSearch;
    @BindView(R.id.id_iv_QR_code)
    ImageView idIvQRCode;
    @BindView(R.id.id_rl_status_bg)
    RelativeLayout idRlStatusBg;
    private IMainPresenter mainPresenter;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        设置界面不跟随系统状态栏和导航栏改变
        FullScreenUtils.setFitsSystem(this);
//        设置界面全屏模式/显示状态栏
        FullScreenUtils.FullScreen(this);
//        设置presenter
        mainPresenter = new MainPresenter(this);

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FullScreenUtils.FullScreen(this);
    }


    @Override
    public void setRecyclerViewData() {

    }

    @Override
    public RecyclerView getRecyclerView() {
        return idRefreshRecyclerViewMain.getRefreshableView();
    }

    @SuppressLint({"ResourceAsColor", "NewApi"})
    @Override
    public void setTitleBackGroundColor(@ColorInt int color,int dy) {
        if (dy > 200) {
            idRlStatusBg.setBackground(getDrawable(R.drawable.shape_oval_main_color));
            idIvSearch.setImageResource(R.drawable.icon_sousuo2);
            idTvSearch.setTextColor(Color.BLACK);
            idTvSearch.setText("搜索关键词");
            idIvQRCode.setImageResource(R.drawable.icon_saoyisao2);
        } else {
            idRlStatusBg.setBackground(getDrawable(R.drawable.shape_oval_white));
            idIvSearch.setImageResource(R.drawable.icon_sousuo);
            idTvSearch.setTextColor(Color.WHITE);
            idTvSearch.setText("搜索关键词");
            idIvQRCode.setImageResource(R.drawable.icon_saoyisao);
        }
        FullScreenUtils.SetStatusBarColor(this, color);
        idLlTitle.setBackgroundColor(color);
    }


}
