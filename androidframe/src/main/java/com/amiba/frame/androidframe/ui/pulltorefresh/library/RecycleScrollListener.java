package com.amiba.frame.androidframe.ui.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * com.custom.wudl.testpulltorefresh.library
 * Created by wudl on 16/6/14.
 */
public abstract class RecycleScrollListener extends RecyclerView.OnScrollListener {

    public RecycleScrollListener(Context context) {
        super();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    /**
     * @param recyclerView The RecyclerView which scrolled.
     * @param dx           The amount of horizontal scroll.
     * @param dy           The amount of vertical scroll.
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

    }
}
