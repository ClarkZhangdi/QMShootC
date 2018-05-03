package com.clark.qmshootc.modules.main.views.viewpager.fragment.persenter.impl;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.clark.qmshootc.modules.main.components.MainActivity;
import com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.adapter.MainRecyclerViewAdapter;
import com.clark.qmshootc.modules.main.views.viewpager.fragment.MainPagerFragment;
import com.clark.qmshootc.modules.main.views.viewpager.fragment.persenter.IMainPagerPresenter;

public class MainPagerPresenter implements IMainPagerPresenter {

    private static final String TAG = MainPagerPresenter.class.getSimpleName();
    private View view;
    private MainPagerFragment mainPagerFragment;
    private int distance = 0;
    private int transparent = 0;

    public MainPagerPresenter(View view) {
        this.view = view;
        mainPagerFragment = (MainPagerFragment) view;
        initRecyclerView();
//        设置滚动距离来改变搜索栏北京
        initSearchBar();
    }


    private void initRecyclerView() {
        mainPagerFragment.getRecyclerView().setLayoutManager(new LinearLayoutManager(mainPagerFragment.getContext()));
        mainPagerFragment.getRecyclerView().setAdapter(new MainRecyclerViewAdapter(mainPagerFragment.getContext()));
    }

    private void initSearchBar() {


        mainPagerFragment.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {

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
