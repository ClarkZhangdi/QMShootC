package com.clark.qmshootc.modules.main.views;

import android.content.Context;
import android.util.AttributeSet;

import com.amiba.frame.androidframe.ui.pulltorefresh.library.PullToRefreshRecyclerView;

public class MainPullToRefreshRecyclerView extends PullToRefreshRecyclerView {
    public MainPullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public MainPullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainPullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public MainPullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }


}
