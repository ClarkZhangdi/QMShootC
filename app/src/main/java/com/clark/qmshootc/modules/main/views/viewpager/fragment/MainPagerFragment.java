package com.clark.qmshootc.modules.main.views.viewpager.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amiba.frame.androidframe.base.BaseFragment;
import com.amiba.frame.androidframe.ui.pulltorefresh.library.PullToRefreshRecyclerView;
import com.clark.qmshootc.R;
import com.clark.qmshootc.common.utils.app.FullScreenUtils;
import com.clark.qmshootc.modules.main.views.viewpager.fragment.persenter.IMainPagerPresenter;
import com.clark.qmshootc.modules.main.views.viewpager.fragment.persenter.impl.MainPagerPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainPagerFragment extends BaseFragment implements IMainPagerPresenter.View {
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
    Unbinder unbinder;

    private View rootView;
    private IMainPagerPresenter mainPagerPresenter;


    public AppCompatActivity getAppCompatActivity() {
        return activity;
    }

    public void setAppCompatActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    private AppCompatActivity activity;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_pager_main, null);
        unbinder = ButterKnife.bind(this, rootView);
        mainPagerPresenter = new MainPagerPresenter(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setRecyclerViewData() {

    }

    @Override
    public RecyclerView getRecyclerView() {
        return idRefreshRecyclerViewMain.getRefreshableView();
    }

    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setTitleBackGroundColor(int color, int dy) {
        if (color == Color.WHITE) {
            idIvStatusBg.setBackground(activity.getDrawable(R.drawable.shape_oval_black));
            idIvSearch.setImageResource(R.drawable.icon_sousuo2);
            idTvSearch.setTextColor(R.color.colorSupportText);
            idTvSearch.setText("搜索关键词");
            idIvQRCode.setImageResource(R.drawable.icon_saoyisao2);
            idViewDivide.setBackgroundColor(R.color.colorDivideLine);
            FullScreenUtils.FullScreen(true, activity);
        } else {
            idIvStatusBg.setBackground(activity.getDrawable(R.drawable.shape_oval));
            idIvSearch.setImageResource(R.drawable.icon_sousuo);
            idTvSearch.setTextColor(Color.WHITE);
            idTvSearch.setText("搜索关键词");
            idIvQRCode.setImageResource(R.drawable.icon_saoyisao);
            idViewDivide.setBackgroundColor(Color.TRANSPARENT);
            FullScreenUtils.FullScreen(false, activity);

        }
        FullScreenUtils.SetStatusBarColor(activity, color);
        idLlTitle.setBackgroundColor(color);
    }
}
