package com.clark.qmshootc.modules.main.presenter.impl;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.clark.qmshootc.R;
import com.clark.qmshootc.modules.main.components.MainActivity;
import com.clark.qmshootc.modules.main.presenter.IMainPresenter;
import com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.adapter.MainRecyclerViewAdapter;
import com.facebook.stetho.common.LogUtil;

public class MainPresenter implements IMainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private View view;
    private MainActivity mainActivity;
    private int distance = 0;
    private int transparent = 0;

    public MainPresenter(IMainPresenter.View view) {
        this.view = view;
        mainActivity = (MainActivity) view;
        initRecyclerView();
//        设置滚动距离来改变搜索栏北京
        initSearchBar();
    }


    private void initRecyclerView() {
        mainActivity.getRecyclerView().setLayoutManager(new LinearLayoutManager(mainActivity));
        mainActivity.getRecyclerView().setAdapter(new MainRecyclerViewAdapter(mainActivity));
    }

    private void initSearchBar() {


        mainActivity.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                distance += dy;
                Log.d(TAG, " dy : " + distance);
                if (distance > 200) {
                    view.setTitleBackGroundColor(Color.WHITE, distance);
                } else {
                    view.setTitleBackGroundColor(Color.TRANSPARENT, distance);
                }
            }
        });
    }
}
