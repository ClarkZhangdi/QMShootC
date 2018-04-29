package com.clark.qmshootc.modules.main.components;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.amiba.frame.androidframe.base.BaseActivity;
import com.amiba.frame.androidframe.ui.pulltorefresh.library.PullToRefreshRecyclerView;
import com.clark.qmshootc.R;
import com.clark.qmshootc.common.utils.app.FullScreenUtils;
import com.clark.qmshootc.modules.main.adapter.MainRecyclerViewAdapter;
import com.clark.qmshootc.modules.main.presenter.IMainPresenter;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainPresenter.View {
    @BindView(R.id.id_refresh_recycler_view_main)
    PullToRefreshRecyclerView idRefreshRecyclerViewMain;
    private RecyclerView recyclerView;
    private List<String> picList;
    private List<Map<String, Object>> channelList;
    private List<Integer> girlList;
    private List<String> normalList;
    private MainRecyclerViewAdapter adapter;
    private RecyclerView refreshableView;


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
//        初始化RecyclerView
        initView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FullScreenUtils.FullScreen(this);
    }


    private void initView() {
        refreshableView = idRefreshRecyclerViewMain.getRefreshableView();
    }

    @Override
    public void setRecyclerViewData() {

    }

    @Override
    public RecyclerView getRecyclerView() {
        return refreshableView;
    }


}
