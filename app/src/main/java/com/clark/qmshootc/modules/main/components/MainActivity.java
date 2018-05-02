package com.clark.qmshootc.modules.main.components;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
    @BindView(R.id.id_iv_QR)
    ImageView idIvQR;
    @BindView(R.id.id_iv_QR_code)
    ImageView idIvQRCode;
    @BindView(R.id.id_iv_status_bg)
    ImageView idIvStatusBg;
    @BindView(R.id.id_iv_search)
    ImageView idIvSearch;
    @BindView(R.id.id_tv_search)
    TextView idTvSearch;
    @BindView(R.id.id_view_divide)
    View idViewDivide;
    @BindView(R.id.id_ll_title)
    ConstraintLayout idLlTitle;


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
    public void setTitleBackGroundColor(@ColorInt int color, int dy) {
        if (color == Color.WHITE) {
            idIvStatusBg.setBackground(getDrawable(R.drawable.shape_oval_black));
            idIvSearch.setImageResource(R.drawable.icon_sousuo2);
            idTvSearch.setHintTextColor(R.color.colorSupportText);
            idTvSearch.setHint("搜索关键词");
            idIvQRCode.setImageResource(R.drawable.icon_saoyisao2);
            idViewDivide.setBackgroundColor(R.color.colorDivideLine);
        } else {
            idIvStatusBg.setBackground(getDrawable(R.drawable.shape_oval));
            idIvSearch.setImageResource(R.drawable.icon_sousuo);
            idTvSearch.setHintTextColor(Color.WHITE);
            idTvSearch.setHint("搜索关键词");
            idIvQRCode.setImageResource(R.drawable.icon_saoyisao);
            idViewDivide.setBackgroundColor(Color.TRANSPARENT);
        }
        FullScreenUtils.SetStatusBarColor(this, color);
        idLlTitle.setBackgroundColor(color);
    }


}
